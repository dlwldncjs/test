package version_Check;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class Ftp_Update {
	private Session session = null;
	private Channel channel = null;
	private ChannelSftp channelSftp = null;

	public void ftp_Update(String host, String userName, String password, int port) throws IOException, SftpException {
		JSch jsch = new JSch();
		try {
			long start = System.currentTimeMillis();
			session = jsch.getSession(userName, host, port);
			session.setPassword("1q2w3e4r!");
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		channelSftp = (ChannelSftp) channel;
	}

	public void download(String dir, String downloadFileName, String path) throws Exception {
		InputStream in = null;
		FileOutputStream out = null;
		try {
			channelSftp.cd(dir);
			in = channelSftp.get(downloadFileName);

		} catch (SftpException se) {
			se.printStackTrace();
		}
		try {
			out = new FileOutputStream(new File(path));
			int i;

			while ((i = in.read()) != -1) {
				out.write(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void disconnection() {
		channelSftp.quit();
	}
}