package com.nixx._001;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.awt.*;

/**
 * @author nixx
 */
@RestController
public class TestController {

    @Resource
    private StudentRepository studentRepository;

    @GetMapping("/test")
    public void test() {
        Student student1 = new Student("1", "张三", 18);
        Student student2 = new Student("2", "李四", 18);
        Student student3 = new Student("3", "王五", 18);

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        System.out.println(studentRepository.findById("1"));
    }


    public static void main(String[] args) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("C:\\Users\\歪比巴卜\\Pictures\\壁纸\\0a7312097d9f490cadd44d294d6cd646.jpg");
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");

        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("123");
        tray.add(trayIcon);

        trayIcon.displayMessage("title", "text", TrayIcon.MessageType.INFO);
    }
}
