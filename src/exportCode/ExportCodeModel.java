package exportCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import org.json.JSONException;

import SnippingCode.Domain.Code;
import SnippingCode.Service.CodesHttpRequest;

public class ExportCodeModel {

  private Code code = new Code();
  private String codeName;
  
  public void setCodeName(String codeName) {
    this.codeName = codeName;
  }
  
  public String getCodeBody(String projectPath) {
    if(this.codeName == null) return "";
    StringBuilder body = new StringBuilder();
    BufferedReader bufferedReader = null;
    try {
      String line;
      bufferedReader = new BufferedReader(new FileReader(projectPath + File.separator + this.codeName));
      while((line = bufferedReader.readLine()) != null) body.append(line+"\n");
    } catch(IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if(bufferedReader != null) bufferedReader.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    
    this.code.setName(this.codeName);
    this.code.setCode(body.toString());
    return body.toString();
  }
  
  public void setCodeTags(Set<String> tags) {
    this.code.setTagSet(tags);
  }
  
  public void uploadCode() {
    CodesHttpRequest request = new CodesHttpRequest();
    code.setDescription("for what ??");
    code.setType("java");
    try {
      request.uploadCode(code, "abdelgawad", "123456");
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
}
