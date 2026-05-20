package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Sistema de notificaciones para la aplicación
 */
public class NotificationManager {
    
    public enum NotificationType {
        SUCCESS(UITheme.COLOR_SUCCESS),
        ERROR(UITheme.COLOR_DANGER),
        WARNING(UITheme.COLOR_WARNING),
        INFO(UITheme.COLOR_INFO);
        
        private final Color color;
        
        NotificationType(Color color) {
            this.color = color;
        }
        
        public Color getColor() {
            return color;
        }
    }
    
    private static JPanel containerPanel;
    
    public static void setContainerPanel(JPanel panel) {
        containerPanel = panel;
    }
    
    public static void showNotification(String title, String message, NotificationType type) {
        if (containerPanel == null) return;
        
        JPanel notificationPanel = createNotificationPanel(title, message, type);
        containerPanel.add(notificationPanel);
        containerPanel.revalidate();
        containerPanel.repaint();
        
        // Auto-remove después de 4 segundos
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    containerPanel.remove(notificationPanel);
                    containerPanel.revalidate();
                    containerPanel.repaint();
                });
            }
        }, 4000);
    }
    
    private static JPanel createNotificationPanel(String title, String message, NotificationType type) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBackground(type.getColor());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        panel.setMaximumSize(new Dimension(400, 80));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(UITheme.COLOR_WHITE);
        titleLabel.setFont(new Font(UITheme.FONT_FAMILY, Font.BOLD, 12));
        
        JLabel messageLabel = new JLabel(message);
        messageLabel.setForeground(UITheme.COLOR_WHITE);
        messageLabel.setFont(UITheme.FONT_LABEL);
        
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);
        textPanel.add(titleLabel, BorderLayout.NORTH);
        textPanel.add(messageLabel, BorderLayout.CENTER);
        
        JButton closeButton = new JButton("✕");
        closeButton.setBackground(new Color(0, 0, 0, 0));
        closeButton.setForeground(UITheme.COLOR_WHITE);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFont(new Font(UITheme.FONT_FAMILY, Font.BOLD, 14));
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener((ActionEvent e) -> {
            Container parent = panel.getParent();
            if (parent != null) {
                parent.remove(panel);
                parent.revalidate();
                parent.repaint();
            }
        });
        
        panel.add(textPanel, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.EAST);
        
        return panel;
    }
    
    public static void showSuccess(String message) {
        showNotification("Éxito", message, NotificationType.SUCCESS);
    }
    
    public static void showError(String message) {
        showNotification("Error", message, NotificationType.ERROR);
    }
    
    public static void showWarning(String message) {
        showNotification("Advertencia", message, NotificationType.WARNING);
    }
    
    public static void showInfo(String message) {
        showNotification("Información", message, NotificationType.INFO);
    }
}
