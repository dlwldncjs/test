package version_Check;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Check_xml {
	private static Object new_version[] = new Object[4];

	public void Check_newxml() {
		JFrame frame = new JFrame("AICRO UPDATE");
		frame.setUndecorated(true);
		JLabel label = new JLabel();
		JProgressBar pgb = new JProgressBar(0, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = frame.getContentPane();
		pgb.setValue(0);
		pgb.setStringPainted(true);
		TitledBorder border;
		border = BorderFactory.createTitledBorder("업데이트");
		border.setTitleColor(Color.WHITE);
		pgb.setBorder(border);
		pgb.setForeground(Color.WHITE);
		pgb.setBackground(Color.BLACK);
		label.setForeground(Color.WHITE);
		label.setText("AICRO 버전 확인중.....");
		content.add(label, BorderLayout.CENTER);
		content.add(pgb, BorderLayout.NORTH);
		frame.setSize(500, 200);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setLocationRelativeTo(null);
		
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("C:\\Users\\JW\\Desktop\\Workspace\\AiCRO-Viewr_DH_Local2_lib\\aa.jpg");
		frame.setIconImage(img);
		MetalLookAndFeel.setCurrentTheme(new MyDefaultMetalTheme());
	    try {
	      UIManager.setLookAndFeel(new MetalLookAndFeel());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }

	    SwingUtilities.updateComponentTreeUI(frame);
		
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder xmlBuilder;
					File xmlfile = new File("C:\\Users\\JW\\Desktop\\Workspace\\down\\main\\Version.xml");
					xmlBuilder = xmlFactory.newDocumentBuilder();
					Document doc = xmlBuilder.parse(xmlfile);
					Element rootElement = doc.getDocumentElement();
					xmlFactory.setIgnoringElementContentWhitespace(true);
					int m = 0;
					NodeList list = rootElement.getChildNodes();
					if (list.getLength() > 0) {
						for (int i = 0; i < list.getLength(); i++) {
							NodeList childlist = list.item(i).getChildNodes();
							if (childlist.getLength() > 0) {
								for (int u = 0; u < childlist.getLength(); u++) {
									if (childlist.item(u).getTextContent() != null) {
										new_version[m] = childlist.item(u).getTextContent();
										new_version[m] = new_version[m].toString();
										System.out.println(new_version[m]);
										m++;
									}
								}
							}
						}
					}
					int i = 0;
					if(main.Main_Version.equals(new_version[0])) {
						pgb.setValue(25);
						pgb.setStringPainted(true);
						label.setText("AICRO 최신버전이 확인되었습니다.");
						Thread.sleep(1000);
						if(Uploader.Uploder_Version.equals(new_version[1])) {
							label.setText("AICRO-Uploader 버전 확인중.....");
							Thread.sleep(3000);
							pgb.setValue(50);
							pgb.setStringPainted(true);
							label.setText("AICRO-Uploader 최신버전이 확인되었습니다.");
							Thread.sleep(1000);
						} else {
							label.setText("AICRO-Uploader 최신버전으로 업데이트 중.....");
							for(i = 25; i < 50; i++) {
								pgb.setValue(i);
								pgb.setStringPainted(true);
								Thread.sleep(50);
							}
							new Main_download().Uploader_download();
							pgb.setValue(50);
							label.setText("AICRO-Uploader 업데이트가 완료되었습니다.");
							Thread.sleep(1000);
						}
						if(Downloader.Downloder_Version.equals(new_version[2])) {
							label.setText("AICRO-Downloader 버전 확인중.....");
							Thread.sleep(3000);
							pgb.setValue(75);
							label.setText("AICRO-Downloader 최신버전이 확인되었습니다.");
							Thread.sleep(1000);
							pgb.setStringPainted(true);
						} else {
							label.setText("AICRO-Downloader 최신버전으로 업데이트 중.....");
							for(i = 50; i < 75; i++) {
								pgb.setValue(i);
								pgb.setStringPainted(true);
								Thread.sleep(50);
							}
							new Main_download().Downloader_download();
							pgb.setValue(75);
							label.setText("AICRO-Downloader 업데이트가 완료되었습니다.");
							Thread.sleep(1000);
						}
						if(Viewer.Viewer_Version.equals(new_version[3])) {
							label.setText("AICRO-Viewer 버전 확인중.....");
							Thread.sleep(3000);
							pgb.setValue(100);
							pgb.setStringPainted(true);
							label.setText("AICRO-Viewer 최신버전이 확인되었습니다.");
							Thread.sleep(1000);
							frame.dispose();
						} else {
							label.setText("AICRO-Viewer 최신버전으로 업데이트 중.....");
							for(i = 75; i < 100; i++) {
								pgb.setValue(i);
								pgb.setStringPainted(true);
								Thread.sleep(50);
							}
							new Main_download().Viewer_download();
							pgb.setValue(100);
							label.setText("AICRO-Viewer 업데이트가 완료되었습니다.");
							Thread.sleep(1000);
						}
					} else {
						label.setText("AICRO 최신버전으로 업데이트 중.....");
						for(i = 0; i < 100; i++) {
							pgb.setValue(i);
							pgb.setStringPainted(true);
							Thread.sleep(50);
						}
						new Main_download().Main_download();
						label.setText("AICRO 업데이트가 완료되었습니다.");
						Thread.sleep(1000);
						label.setText("AICRO를 실행합니다.");
						Thread.sleep(1000);
						frame.dispose();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		thread.start();
	}
	
	class MyDefaultMetalTheme extends DefaultMetalTheme {

		
		}
}
