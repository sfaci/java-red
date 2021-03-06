package org.sfsoft.clientesmtp;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Ejemplo Java que envía un mensaje de correo a un servidor SMTP
 * @author Santiago Faci
 * @version curso 2014-2015
 * 
 * Se ha empleado la librería JavaMail
 * https://java.net/projects/javamail/pages/Home#Download_JavaMail_1.5.1_Release
 * 
 * Para hacer pruebas se instala hmailserver en el equipo
 * http://www.hmailserver.com/
 *
 */
public class ClienteSMTP {

	public static final String SERVIDOR = "midominio.com";
	public static final String FROM = "yo@midominio.com";
	public static final String TO = "destinatario@undominio.com";
	public static final String CC = "copia@undominio.com";
	public static final String BCC = "copiaoculta@mundominio.com";
	public static final String SUBJECT = "Asunto del mensaje";
	public static final String BODY = "Este mensaje es una prueba del cliente SMTP en Java";
	
	public static void main(String args[]) {
		
		try {
			// Inicializa una sesión
			Properties props = System.getProperties();
			props.put("JavaMailSMTP", SERVIDOR);
			Session sesion = Session.getDefaultInstance(props, null);
			
			// Crea el mensaje
			Message mensaje = new MimeMessage(sesion);
			mensaje.setFrom(new InternetAddress(SERVIDOR));
			mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO, false));
			// Añade destinatarios en copia/copia oculta
			//mensaje.setRecipients(Message.RecipientType.CC, InternetAddress.parse(CC, false));
			//mensaje.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(BCC, false));
			mensaje.setSubject(SUBJECT);
			mensaje.setText(BODY);
			// Fecha de envío
			mensaje.setSentDate(new Date());
			
			// Envía el mensaje
			System.out.println("Enviando mensaje . . .");
			Transport.send(mensaje);
			System.out.println("Mensaje enviado.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
