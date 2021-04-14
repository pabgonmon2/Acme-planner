<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.task.list.label.title" path="title" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.startDate" path="startDate" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.endDate" path="endDate" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.workFlow" path="workFlow" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.description" path="description" width="20%"/>
	<acme:list-column code="anonymous.task.list.label.publicTask" path="publicTask" width="10%"/>
</acme:list>