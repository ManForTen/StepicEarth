import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Main extends Canvas {
    static int width = 900, height = 900;

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Для закрытия программы
        frame.setTitle("Планеты");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height + 30);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.BLACK);
        BufferedImage screamHead = ImageIO.read(new URL("https://id-static.z-dn.net/files/d31/8d637386b3620165aaa9eedb7707f938.jpg"));
        BufferedImage sunnyHead = ImageIO.read(new URL("https://sun9-78.userapi.com/s/v1/if1/0rG4JkYwCYteGDW7GxwEqAr7zg62OtnnrIdRruAhms4ip4r654fFyVD5QsWNacfsCK5KsT0W.jpg?size=100x100&quality=96&crop=59,47,408,408&ava=1"));
        int screamHeadWidth = screamHead.getWidth();
        int screamHeadHeight = screamHead.getHeight();
        int sunnyHeadWidth = sunnyHead.getWidth();
        int sunnyHeadHeight = sunnyHead.getHeight();
        JLabel wIcon = new JLabel(new ImageIcon(screamHead)); // Создаем объект с картинкой, который будем размещать и двигать
        JLabel wIcon2 = new JLabel(new ImageIcon(sunnyHead));
        int startX = width / 2 - screamHeadWidth / 2, startY = height / 20; // Задаём координаты для движущейся картинки
        int newStartX = width / 2 - sunnyHeadWidth / 2, newStartY = height / 2 - sunnyHeadHeight / 2; // Координаты для картинки по центру
        wIcon.setBounds(newStartX, newStartY, sunnyHeadWidth, sunnyHeadHeight); // Устанавливаем начальное положение картинки, которая по центру
        wIcon2.setBounds(startX,startY,screamHeadWidth,screamHeadHeight); // Координаты для головы, которая будет кружить
        frame.add(wIcon); // Добавляем картинку на форму
        frame.add(wIcon2); // Картинка по центру


        int radius=Math.max(width, height)/2-Math.max(sunnyHeadWidth, sunnyHeadHeight); // Находим оптимальный радиус окружности
        int deltaX=width/2-sunnyHeadWidth/2, deltaY=height/2-sunnyHeadHeight/2; // Константы для смещения картинки, чтобы не рассчитывать их постоянно

        for (int t = 0; t < 360;) {
            try { // Таймер
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            wIcon2.setBounds((int) (radius*Math.cos(t*Math.PI/180))+deltaX, (int) (radius*Math.sin(t*Math.PI/180))+deltaY, sunnyHeadWidth, sunnyHeadHeight);
            wIcon2.repaint(); // Перерисовываем картинку
            t = t == 359 ? 0 : t+1; // Формула, которая обеспечивает постоянный цикл и обнуление t при 359
        }

    }
}