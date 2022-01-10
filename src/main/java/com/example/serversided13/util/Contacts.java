package com.example.serversided13.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import com.example.serversided13.models.ContactModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.ui.Model;

public class Contacts {
  private static final Logger logger = LoggerFactory.getLogger(Contacts.class);

  public void saveContact(ContactModel contactModel, Model model, ApplicationArguments appArgs) {
    String dataFilename = contactModel.getId();

    Set<String> optNames = appArgs.getOptionNames();
    String[] optNamesArray = optNames.toArray(new String[optNames.size()]);
    List<String> optValues = appArgs.getOptionValues(optNamesArray[0]);

    PrintWriter printWriter = null;
    try {
      FileWriter fileWriter = new FileWriter(
          System.getProperty("user.dir") + (String) optValues.get(0) + "//" + dataFilename, Charset.forName("utf-8"));
      printWriter = new PrintWriter(fileWriter);
      printWriter.println(contactModel.getName());
      printWriter.println(contactModel.getEmail());
      printWriter.println(contactModel.getPhoneNumber());
    } catch (IOException e) {
      logger.error(e.getMessage());
    } finally {
      printWriter.close();
    }
    // contactModel in parameter is from the form. Objects with different use cases should be isolated, this is a good practice preached by kenneth.
    model.addAttribute("contact",
        new ContactModel(contactModel.getId(),
            contactModel.getName(),
            contactModel.getEmail(),
            contactModel.getPhoneNumber()));

  }

  public void getContactById(Model model, String contactId, ApplicationArguments appArgs) {
    Set<String> optNames = appArgs.getOptionNames();
    String[] optNamesArray = optNames.toArray(new String[optNames.size()]);
    List<String> optValues = appArgs.getOptionValues(optNamesArray[0]);

    ContactModel contactModelResp = new ContactModel();

    try {
      Path filePath = new File(System.getProperty("user.dir") + (String) optValues.get(0) + "/" + contactId).toPath();
      List<String> stringList = Files.readAllLines(filePath, Charset.forName("utf-8"));
      contactModelResp.setName(stringList.get(0));
      contactModelResp.setEmail(stringList.get(1));
      contactModelResp.setPhoneNumber(stringList.get(2));

    } catch (IOException e) {
      logger.error(e.getMessage());
    }
    model.addAttribute("contact", contactModelResp);
  }

}
