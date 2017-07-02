package com.bss.googleutils;


import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.bss.domain.Properties;
import com.bsscorp.utils.Constants;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.Sheets.Spreadsheets.Create;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.ValueRange;

public class GoogleSheetWrapper {
	
	private static final String APPLICATION_NAME = "Money Manager";
	private static String credentialStore;
	
	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/** Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials
	 * at ~/.credentials/sheets.googleapis.com-java-quickstart
	 */
	private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS,DriveScopes.DRIVE);

	static {
		try {
			credentialStore = Properties.getValue(Constants.GOOGLE_DRIVE_CREDENTIAL_STORE);
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static Credential authorize() throws IOException {
		
		Credential c1 = null;
		
		try {

//			c1 = GoogleCredential.fromStream(new FileInputStream("I:\\Git_Repo\\GoogleDriveIntegration-b1ad759d9ba1.json"))
			c1 = GoogleCredential.fromStream(new FileInputStream(credentialStore)).createScoped(SCOPES);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c1;
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

		Credential credential = authorize();

		return new Sheets.Builder(httpTransport, jsonFactory, credential)
				.setApplicationName("Google-SheetsSample/0.1")
				.build();
	}
    
	public static String uploadSheet(String folderId, String sheetName, Map<String, List<List<Object>>> dataMap, List<String> keySet) throws IOException{
		String sheetId = null;
		
		Sheets service = getSheetsService();
		Drive driveService = getDriveService();
		
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