package utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Tema unificado para toda la aplicación
 * Color: Azul profesional (#2C3E50)
 * Tipografía: Segoe UI
 */
public class UITheme {
    
    // Colores principales
    public static final Color COLOR_PRIMARY = new Color(44, 62, 80);      // Azul oscuro
    public static final Color COLOR_SECONDARY = new Color(52, 152, 219);  // Azul claro
    public static final Color COLOR_ACCENT = new Color(230, 126, 34);     // Naranja
    public static final Color COLOR_BG = new Color(236, 240, 241);        // Gris muy claro
    public static final Color COLOR_BG_DARK = new Color(224, 230, 236);   // Gris oscuro
    public static final Color COLOR_TEXT = new Color(44, 62, 80);         // Texto oscuro
    public static final Color COLOR_TEXT_LIGHT = new Color(149, 165, 166); // Texto gris
    public static final Color COLOR_WHITE = new Color(255, 255, 255);
    public static final Color COLOR_SUCCESS = new Color(39, 174, 96);
    public static final Color COLOR_DANGER = new Color(231, 76, 60);
    public static final Color COLOR_WARNING = new Color(241, 196, 15);
    public static final Color COLOR_INFO = new Color(52, 152, 219);
    
    // Tipografía
    public static final String FONT_FAMILY = "Segoe UI";
    public static final Font FONT_TITLE = new Font(FONT_FAMILY, Font.BOLD, 28);
    public static final Font FONT_SUBTITLE = new Font(FONT_FAMILY, Font.BOLD, 18);
    public static final Font FONT_LABEL = new Font(FONT_FAMILY, Font.PLAIN, 12);
    public static final Font FONT_BUTTON = new Font(FONT_FAMILY, Font.BOLD, 12);
    public static final Font FONT_SMALL = new Font(FONT_FAMILY, Font.PLAIN, 10);
    
    // Tamaños
    public static final int BUTTON_HEIGHT = 35;
    public static final int BUTTON_WIDTH = 120;
    public static final int PADDING = 10;
    public static final int BORDER_RADIUS = 5;
    
    public static void applyTheme(JPanel panel) {
        panel.setBackground(COLOR_BG);
        panel.setForeground(COLOR_TEXT);
    }
    
    public static void applyThemeDark(JPanel panel) {
        panel.setBackground(COLOR_BG_DARK);
        panel.setForeground(COLOR_TEXT);
    }
    
    public static void styleButton(JButton button) {
        button.setBackground(COLOR_SECONDARY);
        button.setForeground(COLOR_WHITE);
        button.setFont(FONT_BUTTON);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    }
    
    public static void styleButtonSuccess(JButton button) {
        button.setBackground(COLOR_SUCCESS);
        button.setForeground(COLOR_WHITE);
        button.setFont(FONT_BUTTON);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    }
    
    public static void styleButtonDanger(JButton button) {
        button.setBackground(COLOR_DANGER);
        button.setForeground(COLOR_WHITE);
        button.setFont(FONT_BUTTON);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    }
    
    public static void styleLabel(JLabel label) {
        label.setForeground(COLOR_TEXT);
        label.setFont(FONT_LABEL);
    }
    
    public static void styleTitleLabel(JLabel label) {
        label.setForeground(COLOR_PRIMARY);
        label.setFont(FONT_TITLE);
    }
    
    public static void styleSubtitleLabel(JLabel label) {
        label.setForeground(COLOR_SECONDARY);
        label.setFont(FONT_SUBTITLE);
    }
    
    public static void styleTextField(JTextField field) {
        field.setBackground(COLOR_WHITE);
        field.setForeground(COLOR_TEXT);
        field.setFont(FONT_LABEL);
        field.setBorder(new LineBorder(COLOR_SECONDARY, 1, true));
        field.setPreferredSize(new Dimension(200, 30));
    }
    
    public static void styleTable(JTable table) {
        table.setBackground(COLOR_WHITE);
        table.setForeground(COLOR_TEXT);
        table.setFont(FONT_LABEL);
        table.getTableHeader().setBackground(COLOR_PRIMARY);
        table.getTableHeader().setForeground(COLOR_WHITE);
        table.getTableHeader().setFont(new Font(FONT_FAMILY, Font.BOLD, 12));
        table.setRowHeight(25);
        table.setGridColor(COLOR_BG);
        table.setSelectionBackground(COLOR_SECONDARY);
        table.setSelectionForeground(COLOR_WHITE);
    }
    
    public static void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setBackground(COLOR_WHITE);
        comboBox.setForeground(COLOR_TEXT);
        comboBox.setFont(FONT_LABEL);
    }
    
    public static JPanel createCardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_WHITE);
        panel.setBorder(BorderFactory.createLineBorder(COLOR_BG_DARK, 1));
        return panel;
    }
}
