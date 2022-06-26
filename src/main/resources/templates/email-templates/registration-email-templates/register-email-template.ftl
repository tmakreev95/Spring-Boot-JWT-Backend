<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="format-detection" content="date=no" />
    <meta name="format-detection" content="address=no" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="x-apple-disable-message-reformatting" content=""/>
    <link href="https://fonts.googleapis.com/css?family=Muli:400,400i,700,700i" rel="stylesheet" />
    <title>Registration Email</title>

    <style type="text/css" media="screen">
		/* Linked Styles */
		body { padding:0 !important; margin:0 !important; display:block !important;
		min-width:100% !important; width:100% !important; background:#001736;
		-webkit-text-size-adjust:none }
		a { color:#66c7ff; text-decoration:none }
		p { padding:0 !important; margin:0 !important }
		img { -ms-interpolation-mode: bicubic; }
		.mcnPreviewText { display: none !important; }


		/* Mobile styles */
		@media only screen and (max-device-width: 480px), only screen and (max-width: 480px) {
			.mobile-shell { width: 100% !important; min-width: 100% !important; }
			.bg { background-size: 100% auto !important; -webkit-background-size: 100% auto !important; }

			.text-header,
			.m-center { text-align: center !important; }

			.center { margin: 0 auto !important; }
			.container { padding: 20px 10px !important }

			.td { width: 100% !important; min-width: 100% !important; }

			.m-br-15 { height: 15px !important; }
			.p30-15 { padding: 30px 15px !important; }

			.m-td,
			.m-hide { display: none !important; width: 0 !important; height: 0 !important; font-size: 0 !important;
			 line-height: 0 !important; min-height: 0 !important; }

			.m-block { display: block !important; }

			.fluid-img img { width: 100% !important; max-width: 100% !important; height: auto !important; }

			.column,
			.column-top,
			.column-empty,
			.column-empty2,
			.column-dir-top { float: left !important; width: 100% !important; display: block !important; }

			.column-empty { padding-bottom: 10px !important; }
			.column-empty2 { padding-bottom: 30px !important; }

			.content-spacing { width: 15px !important; }
		}
	</style>
</head>
    <body class="body" style="padding:0 !important; margin:0 !important; display:block !important;
     min-width:100% !important; width:100% !important; background:#001736; -webkit-text-size-adjust:none;">

    <img src="images/logo.png" border="0" alt="" style="margin: 20px auto; display: block;width: 200px; height:auto;"/>

    <table width="60%" border="0" cellspacing="0" cellpadding="0" style="margin: 0 auto;">
        <tr>
            <td style="padding-bottom: 10px;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="tbrr p30-15" style="padding: 60px 30px; border-radius:26px 26px 0px 0px;" bgcolor="#12325c">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td class="h1 pb25" style="color:#ffffff; font-family:'Muli',
                                        Arial,sans-serif; font-size:40px; line-height:46px; text-align:center;
                                        padding-bottom:25px;">Welcome, ${firstName} ${lastName}</td>
                                </tr>
                                <tr>
                                    <td class="text-center pb25" style="color:#c1cddc; font-family:'Muli',
                                        Arial,sans-serif; font-size:16px; line-height:30px;
                                        text-align:center; padding-bottom:25px;">
                                        We have sent to ${email} a conformation code, so you can complete your
                                        registration and start using your newly created account in our application.
                                        Proceed to the link for completing the registration process!</td>
                                </tr>
                                <!-- Button -->
                                <tr>
                                    <td align="center">
                                        <table class="center" border="0" cellspacing="0" cellpadding="0" style="text-align:center;">
                                            <tr>
                                                <td class="pink-button text-button">
                                                    <#assign url="http://localhost:8080/verify-token/${verificationToken}">
                                                    <a href="${url}" target="_blank" class="link-white pink-button text-button"
                                                       style="color:#ffffff; text-decoration:none;background:#ff6666;
                                                        color:#fff; font-family:'Muli', Arial,sans-serif;
                                                        font-size:14px; line-height:18px; padding:12px 30px;
                                                        text-align:center; border-radius:0px 22px 22px 22px;
                                                        font-weight:bold;">VERIFY EMAIL</a>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    </body>
</html>
