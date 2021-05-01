<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form>
	<acme:form-textbox code= "manager.mytasks.form.label.title" path="title"/>
	<acme:form-moment code= "manager.mytasks.form.label.startDate" path="startDate"/>
	<acme:form-moment code= "manager.mytasks.form.label.endDate" path="endDate"/>
	<acme:form-textarea code= "manager.mytasks.form.label.description" path="description"/>
	<acme:form-checkbox code="manager.mytasks.form.label.publicTask" path="publicTask" />
	<acme:form-url code="manager.mytasks.form.label.url" path="url"/>
	
	<acme:form-submit code= "manager.mytasks.form.button.create" action="/manager/task/create"/>
	<acme:form-submit code="manager.mytasks.form.button.delete" action="/manager/task/delete"/>
	<acme:form-return  code= "manager.mytasks.form.button.return"/>
</acme:form>

