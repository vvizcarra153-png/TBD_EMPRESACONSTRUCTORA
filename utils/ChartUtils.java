package utils;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Utilidades para crear gráficos simples
 */
public class ChartUtils {
    
    /**
     * Crea un gráfico de barras simple
     */
    public static JPanel createBarChart(String title, Map<String, Integer> data) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int width = getWidth();
                int height = getHeight();
                int padding = 50;
                int maxValue = data.values().stream().mapToInt(Integer::intValue).max().orElse(100);
                
                g2d.setColor(UITheme.COLOR_WHITE);
                g2d.fillRect(0, 0, width, height);
                
                // Título
                g2d.setFont(UITheme.FONT_SUBTITLE);
                g2d.setColor(UITheme.COLOR_PRIMARY);
                g2d.drawString(title, 20, 30);
                
                // Ejes
                g2d.setColor(UITheme.COLOR_PRIMARY);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(padding, height - padding, width - padding, height - padding);
                g2d.drawLine(padding, padding, padding, height - padding);
                
                int numBars = data.size();
                int barWidth = (width - 2 * padding) / (numBars * 2);
                int x = padding + barWidth;
                
                int index = 0;
                for (Map.Entry<String, Integer> entry : data.entrySet()) {
                    int barHeight = (int) ((entry.getValue() / (float) maxValue) * (height - 2 * padding));
                    
                    // Barra
                    g2d.setColor(UITheme.COLOR_SECONDARY);
                    g2d.fillRect(x, height - padding - barHeight, barWidth, barHeight);
                    
                    // Borde
                    g2d.setColor(UITheme.COLOR_PRIMARY);
                    g2d.setStroke(new BasicStroke(1));
                    g2d.drawRect(x, height - padding - barHeight, barWidth, barHeight);
                    
                    // Etiqueta
                    g2d.setFont(UITheme.FONT_LABEL);
                    g2d.setColor(UITheme.COLOR_TEXT);
                    g2d.drawString(entry.getKey(), x - 15, height - padding + 20);
                    
                    // Valor
                    g2d.drawString(String.valueOf(entry.getValue()), x - 10, height - padding - barHeight - 5);
                    
                    x += barWidth * 2;
                    index++;
                }
            }
        };
    }
    
    /**
     * Crea un gráfico de pastel simple
     */
    public static JPanel createPieChart(String title, Map<String, Integer> data) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int width = getWidth();
                int height = getHeight();
                
                g2d.setColor(UITheme.COLOR_WHITE);
                g2d.fillRect(0, 0, width, height);
                
                // Título
                g2d.setFont(UITheme.FONT_SUBTITLE);
                g2d.setColor(UITheme.COLOR_PRIMARY);
                g2d.drawString(title, 20, 30);
                
                int total = data.values().stream().mapToInt(Integer::intValue).sum();
                int centerX = width / 3;
                int centerY = height / 2;
                int radius = Math.min(width, height) / 3;
                
                int startAngle = 0;
                Color[] colors = {UITheme.COLOR_SECONDARY, UITheme.COLOR_SUCCESS, UITheme.COLOR_WARNING, UITheme.COLOR_DANGER};
                int colorIndex = 0;
                
                int legendX = centerX + radius + 50;
                int legendY = 50;
                
                for (Map.Entry<String, Integer> entry : data.entrySet()) {
                    int arcAngle = (int) ((entry.getValue() / (float) total) * 360);
                    
                    g2d.setColor(colors[colorIndex % colors.length]);
                    g2d.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, startAngle, arcAngle);
                    
                    g2d.setColor(UITheme.COLOR_TEXT);
                    g2d.setFont(UITheme.FONT_LABEL);
                    g2d.fillRect(legendX, legendY, 15, 15);
                    g2d.setColor(colors[colorIndex % colors.length]);
                    g2d.fillRect(legendX + 1, legendY + 1, 13, 13);
                    
                    g2d.setColor(UITheme.COLOR_TEXT);
                    g2d.drawString(entry.getKey() + " (" + entry.getValue() + ")", legendX + 25, legendY + 12);
                    
                    startAngle += arcAngle;
                    legendY += 25;
                    colorIndex++;
                }
            }
        };
    }
}
