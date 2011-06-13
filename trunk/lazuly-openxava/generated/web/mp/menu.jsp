<%@ taglib prefix="mp" tagdir="/WEB-INF/tags/common" %>
<%
 request.setAttribute("ctx", request.getContextPath()); 
 %>
<div class='portlet' style='margin: 4px'>

<div id="menuheader">
  	<span id="menutitle">Conference menu 
      <a href="/conference/xava/homeMenu.jsp" ><img src="/conference/xava/images/home.gif"></a>
    </span>
  	<span id="menuPowerByMP">
      <a href="http://minuteproject.wikispaces.com" >powered by minuteProject</a>
    </span>		
</div>


<table id="menuTable" border="0" width="100%" cellspacing="0" cellpadding="0" style="margin-top: 2px;">
<tr>

<td>

<div id="searchbar">
<div class="menustyle" id="menu">
  <ul class="menubar" id="dmenu">
    <li class="topitem">
      <a href="#" onclick="return false;">
      Admin
      </a>
	  <ul class="submenu">
        <li><a href="home.jsp?application=conference&module=Country" >Country</a></li>
        <!--<li><hr color="black"></li>-->
        <li><a href="home.jsp?application=conference&module=Role" >Role</a></li>
	  </ul>
	</li>
    <li class="topitem">
      <a href="#" onclick="return false;">
      Conference
      </a>
	  <ul class="submenu">
        <li><a href="home.jsp?application=conference&module=Address" >Address</a></li>
        <!--<li><hr color="black"></li>-->
        <li><a href="home.jsp?application=conference&module=Conference" >Conference</a></li>
        <!--<li><hr color="black"></li>-->
        <li><a href="home.jsp?application=conference&module=ConferenceFeedback" >Conference feedback</a></li>
        <!--<li><hr color="black"></li>-->
        <li><a href="home.jsp?application=conference&module=ConferenceMember" >Conference member</a></li>
        <!--<li><hr color="black"></li>-->
        <li><a href="home.jsp?application=conference&module=Evaluation" >Evaluation</a></li>
        <!--<li><hr color="black"></li>-->
        <li><a href="home.jsp?application=conference&module=Presentation" >Presentation</a></li>
        <!--<li><hr color="black"></li>-->
        <li><a href="home.jsp?application=conference&module=PresentationPlace" >Presentation place</a></li>
        <!--<li><hr color="black"></li>-->
        <li><a href="home.jsp?application=conference&module=Speaker" >Speaker</a></li>
        <!--<li><hr color="black"></li>-->
        <li><a href="home.jsp?application=conference&module=Sponsor" >Sponsor</a></li>
	  </ul>
	</li>
<!-- views -->
    <li class="topitem"><a href="#" onclick="return false;">Statistics</a>
	  <ul class="submenu">
        <li><a href="home.jsp?application=conference&module=MemberPerRoleCountryAndConference" >Member per role country and conference</a></li>
        <li><a href="home.jsp?application=conference&module=MemberPerCountryAndConference" >Member per country and conference</a></li>
	  </ul>
	</li>
  </ul>
  </div>

</div>
</td>

</tr>
</table>

</div>

</div>

