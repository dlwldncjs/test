package version_Check;

public class Main_download {
	public void Main_download() throws Exception {
		Ftp_Update sftp = new Ftp_Update();
		sftp.ftp_Update("asanbigdata.iptime.org", "admin", "1q2w3e4r!", 22);
		sftp.download("/update/main/viewer/", "viewer.txt",
				"C:\\Users\\JW\\Desktop\\Workspace\\down\\main\\viewer\\viewer.txt");
		sftp.download("/update/main/upload/", "upload.txt",
				"C:\\Users\\JW\\Desktop\\Workspace\\down\\main\\uploader\\uploader.txt");
		sftp.download("/update/main/download/", "download.txt",
				"C:\\Users\\JW\\Desktop\\Workspace\\down\\main\\downloader\\downloader.txt");
		sftp.disconnection();

	}

	public void Uploader_download() throws Exception {
		Ftp_Update sftp = new Ftp_Update();
		sftp.ftp_Update("asanbigdata.iptime.org", "admin", "1q2w3e4r!", 22);
		sftp.download("/update/main/uploader/", "uploader.txt",
				"C:\\Users\\JW\\Desktop\\Workspace\\down\\main\\uploader\\uploader.txt");
		sftp.disconnection();

	}

	public void Downloader_download() throws Exception {
		Ftp_Update sftp = new Ftp_Update();
		sftp.ftp_Update("asanbigdata.iptime.org", "admin", "1q2w3e4r!", 22);
		sftp.download("/update/main/downloader/", "downloader.txt",
				"C:\\Users\\JW\\Desktop\\Workspace\\down\\main\\downloader\\downloader.txt");
		sftp.disconnection();

	}

	public void Viewer_download() throws Exception {
		Ftp_Update sftp = new Ftp_Update();
		sftp.ftp_Update("asanbigdata.iptime.org", "admin", "1q2w3e4r!", 22);
		sftp.download("/update/main/viewer/", "viewer.txt",
				"C:\\Users\\JW\\Desktop\\Workspace\\down\\main\\viewer\\viewer.txt");
		sftp.disconnection();

	}
}
