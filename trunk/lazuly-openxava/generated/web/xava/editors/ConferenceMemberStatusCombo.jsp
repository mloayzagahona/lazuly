<jsp:useBean id="style" class="org.openxava.web.style.Style" scope="request"/> 
 
<%@ include file="validValueEditorCommon.jsp"%>

<% 
  String fvalue = (String) request.getAttribute(propertyKey + ".fvalue"); 
  if (editable) {  
%> 
  <select name="status">
  <option value="" ></option>
<%
   String [] values = new String[] {
     "PENDING",
     "ACTIVE"
   };

  for (int i = 0; i<values.length; i++) { 
    String aa = values[i];
    String selected = (fvalue.equals(aa)) ?"selected":""; 
    
    //TODO if not mandatory=> introduce empty choice
%>
 <option value="<%=aa%>" <%=selected%>><%=aa%></option>
<%
 }
%>
  </select>
 
<%  
} else {
%> 
  <input type="text" disabled value="<%=fvalue%>" />
<%
}
%>



