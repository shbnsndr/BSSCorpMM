package com.bss.googleutils;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.sheets.v4.Sheets.Spreadsheets.Create;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GoogleSheetWrapper {
	
	/** Application name. */
	private static final String APPLICATION_NAME =
			"Google Sheets API Java Quickstart";

	/** Directory to store user credentials for this application. */
//	private static final java.io.File DATA_STORE_DIR = new java.io.File(
//			".credentials/sheets.googleapis.com-java-quickstart");
	private static final java.io.File DATA_STORE_DIR = new java.io.File(
			"/var/lib/openshift/589fe52c2d5271c63f000257/app-root/data/.credentials/sheets.googleapis.com-java-quickstart");
	
	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY =
			JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/** Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials
	 * at ~/.credentials/sheets.googleapis.com-java-quickstart
	 */
	private static final List<String> SCOPES =
			Arrays.asList(SheetsScopes.SPREADSHEETS,DriveScopes.DRIVE);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static Credential authorize() throws IOException {
		// Load client secrets.
		InputStream in = GoogleSheetWrapper.class.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets =
				GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow =
				new GoogleAuthorizationCodeFlow.Builder(
						HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(DATA_STORE_FACTORY)
				.setAccessType("offline")
				.build();
		Credential credential = new AuthorizationCodeInstalledApp(
				flow, new LocalServerReceiver()).authorize("user");
		System.out.println(
				"Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	/**
	 * Build and return an authorized Sheets API client service.
	 * @return an authorized Sheets API client service
	 * @throws IOException
	 */
	public static Sheets getSheetsService() throws IOException {
		Credential credential = authorize();
		return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}
	
	public static Drive getDriveService() throws IOException {
		Credential credential = authorize();
		return new Drive.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}

	public static Sheets createSheetsService() throws IOException, GeneralSecurityException {
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

		// TODO: Change placeholder below to generate authentication credentials. See
		// https://developers.google.com/sheets/quickstart/java#step_3_set_up_the_sample
		//
		// Authorize using one of the following scopes:
		//   "https://www.googleapis.com/auth/drive"
		//   "https://www.googleapis.com/auth/spreadsheets"
		Credential credential = authorize();

		return new Sheets.Builder(httpTransport, jsonFactory, credential)
				.setApplicationName("Google-SheetsSample/0.1")
				.build();
	}
    
	public static String uploadSheet(String folderId, String sheetName, Map<String, List<List<Object>>> dataMap, List<String> keySet) throws IOException{
		String sheetId = null;
		
		Sheets service = getSheetsService();
		Drive driveService = getDriveService();
		
//		Set<String> keySet = dataMap.keySet();
		
		//Create Sheet
		Spreadsheet value = new Spreadsheet();
		SpreadsheetProperties prop = new SpreadsheetProperties();
		prop.setTitle(sheetName);
		value.setProperties(prop);
		
		List<Sheet> shList = new ArrayList<Sheet>();
		for (String dataKey : keySet) {
			SheetProperties shProp = new SheetProperties();
			shProp.setTitle(dataKey);
			
			Sheet sh = new Sheet();
			sh.setProperties(shProp);
			
			shList.add(sh);
		}
		
		value.setSheets(shList);
		
		Create request = service.spreadsheets().create(value);
		Spreadsheet sheetResponse = request.execute();
		sheetId = sheetResponse.getSpreadsheetId();
		System.out.println("Sheet id :: "+sheetId);
		
		//Batch Update Sheet
		for (String dataKey : keySet) {
			
			List<List<Object>> dataList = dataMap.get(dataKey);
			
			int rows=dataList.size()+10;
			
			String valueRange = dataKey+"!"+"A1:Z"+rows;
			
			ValueRange oRange = new ValueRange();
			oRange.setRange(valueRange); // I NEED THE NUMBER OF THE LAST ROW
			oRange.setValues(dataList);

			List<ValueRange> oList = new ArrayList<ValueRange>();
			oList.add(oRange);
			
			BatchUpdateValuesRequest oRequest = new BatchUpdateValuesRequest();
			oRequest.setValueInputOption("RAW");
			oRequest.setData(oList);

			@SuppressWarnings("unused")
			BatchUpdateValuesResponse oResp1 = service.spreadsheets().values().batchUpdate(sheetId, oRequest).execute();
			
		}
		
		File file = driveService.files().get(sheetId)
		        .setFields("parents")
		        .execute();
		StringBuilder previousParents = new StringBuilder();
		for(String parent: file.getParents()) {
		    previousParents.append(parent);
		    previousParents.append(',');
		}
		
		String driveId = (folderId == null)?"0B37UcStoylKAVW1iMkhmc3ppYlU":folderId;
		@SuppressWarnings("unused")
		File replaceRequest = driveService.files().update(sheetId, null)
									.setAddParents(driveId)
									.setRemoveParents(previousParents.toString())
									.setFields("id, parents")
									.execute();
		
		
		return sheetId;
	}

}