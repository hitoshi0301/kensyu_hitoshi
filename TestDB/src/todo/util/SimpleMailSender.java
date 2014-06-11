package todo.util;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SimpleMailSender {
	private static Logger log = Logger.getLogger(SimpleMailSender.class.getName());

	/**アカウント接続時にSSLを利用する場合に利用する */
	private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	/** SMTPサーバ接続ポート：２５番を指定 */
	private final String SMTP_PORT = "465";
	//private final String SMTP_PORT = "465";

	/** メールアカウント */
	private final String AUTH_USER_NAME = "databasetest1991@yahoo.co.jp";

	/** メールアカウントのパスワード */
	private final String AUTH_PASSWORD = "test1234";

	/** SMTPメールホスト */
	private static final String SMTP_HOST ="smtp.mail.yahoo.co.jp";

	public static void main(String argv[]) throws Exception {
		SimpleMailSender mail = new SimpleMailSender();

		mail.sendMessage("databasetest1991@yahoo.co.jp","databasetest1991@yahoo.co.jp","Name","件名","メッセージ本文です");
	}


	public void sendMessage(String toAddr, String fromAddr,String personName,String subject,String message) throws Exception {


		//メールを送信プロパティの作成
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST);
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.host", SMTP_HOST);
		props.put("mail.from",fromAddr);

		//SMTP認証設定
		props.put("mail.smtp.auth", "true");

		/*
		//SMTPソケットポート(SSL用)
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);

		//ソケットクラス名
		//SSLを利用するSMTPサーバはSSL用のソケットファクトリーを利用する
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);

		//SSLフォールバック(SSL用)
		props.put("mail.smtp.socketFactory.fallback", String.valueOf(false));*/

		//メールセッションを確立する
		//セッション確立設定はpropsに格納される
		Session session = Session.getDefaultInstance(props,new Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return (new PasswordAuthentication(AUTH_USER_NAME,AUTH_PASSWORD));
			}
		});
		session.setDebug(true);

		//送信メッセージを生成
		MimeMessage mimeMsg = new MimeMessage(session);

		//送信先(TOのほか、CCやBCCも設定可能
		mimeMsg.setRecipients(Message.RecipientType.TO, toAddr);

		//Fromヘッダ
		InternetAddress fromHeader = new InternetAddress("fromAddr",personName);
		mimeMsg.setFrom(fromHeader);

		//件名
		mimeMsg.setSubject(subject,"ISO-2022-JP");

		//送信時間
		mimeMsg.setSentDate(new Date());

		//本文
		mimeMsg.setText(message, "ISO-2022-JP");

		//メールを送信する
		Transport.send(mimeMsg);
	}
}