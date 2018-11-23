package version_Check;

public class main {
	//∏∂Ω∫≈Õ
	public static final String Main_Version = "2.0";
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Ftp_Update sftp = new Ftp_Update();
		sftp.ftp_Update("asanbigdata.iptime.org", "admin", "1q2w3e4r!", 22);
		sftp.download("/update/", "Version.xml", "C:\\Users\\JW\\Desktop\\Workspace\\down\\main\\New_Version.xml");
		sftp.disconnection();
		new Check_xml().Check_newxml();
	}
}
