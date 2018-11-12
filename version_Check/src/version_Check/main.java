package version_Check;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class main {
	public static final String Main_Version = "1.0";
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Ftp_Update sftp = new Ftp_Update();
		sftp.ftp_Update("asanbigdata.iptime.org", "admin", "1q2w3e4r!", 22);
		sftp.download("/update/", "Version.xml", "C:\\Users\\JW\\Desktop\\Workspace\\down\\main\\New_Version.xml");
		sftp.disconnection();
		new Check_xml().Check_newxml();
		
	}
}
