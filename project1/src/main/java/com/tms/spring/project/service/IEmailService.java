package com.tms.spring.project.service;

public interface IEmailService
{
	boolean SendVerificationMail( String destinationEmailAddress, String recipientName, long recipientUserId );
	boolean VerifyEmailAddress( String token );
	void SendProductStatusNotifMail( String userFirstName, String userLastName, long itemId );
}
