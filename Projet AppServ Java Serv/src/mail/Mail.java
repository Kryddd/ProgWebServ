package mail;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bibliotheque.Abonne;
import documents.Livre;

/**
 * Gère les mails pour notifier les abonnés en cas de retour
 * de livre
 * @author Jacques COUDERC, Arthur CAYET, Antoine PAVY
 * @version 1.0
 */
public class Mail {

	private static final String username = "bibliobrettegrandchaman@gmail.com";
	private static final String password = "motdepasserelativementsecure";
	
	/**
	 * Envoie une notification par mail d'un livre disponible
	 * @param abonnes Les abonnés en attente du livre
	 * @param livre Le livre disponible
	 */
	public static void send(ArrayList<Abonne> abonnes, Livre livre) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			for(Abonne ab : abonnes) {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(ab.getEmail()));
				message.setSubject("Notification BiblioBrette");
				message.setText("Cher·e lecteur·rice " + ab.getPrenom() + " " + ab.getNom() + ","
						+ "\n\n Le livre " + livre.getTitre() + " de " + livre.getAuteur()
						+ " (numéro: " + livre.numero() + ") est disponible dans notre bibliotheque.\n"
						+ "A bientot.\n\nBiblioBrette");
	
				Transport.send(message);
			}


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
