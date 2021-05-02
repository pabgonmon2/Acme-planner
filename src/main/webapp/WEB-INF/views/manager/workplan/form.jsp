<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
 <jstl:if test="${command!='create' }">
	<acme:form-moment readonly="true" code="manager.workplan.form.label.startDate" path="startDate"/>
	<acme:form-moment readonly="true" code="manager.workplan.form.label.endDate" path="endDate"/>
	<acme:form-double readonly="true" code="manager.workplan.form.label.workLoad" path="workLoad"/>
 </jstl:if>
	<acme:form-checkbox code="manager.workplan.form.label.publicPlan" path="publicPlan"/>
	 <jstl:if test="${command=='create' }">
		<label for="tasks"><acme:message code="manager.workplan.form.label.tasks"/></label>
		<select class="form-control selectpicker show-tick" name="tasks1" multiple>
			<jstl:forEach items="${tasksInsert}" var="task">
				<acme:form-option code="${task.title}" value="${task.id}"/>
			</jstl:forEach>
		</select>
	</jstl:if>
	<acme:form-submit code="manager.workplan.form.button.create" action="/manager/workplan/create"/>
	<acme:form-return  code= "manager.workplan.form.button.return"/>
</acme:form>

<br>
<jstl:if test="${command!='create' && tasks.size()>0 }">
<table class="table table-striped table-condensed table-hover nowrap w-100"> 
	<thead><tr>
		<th width="20%"><acme:message code="manager.workplan.tasks.title"/></th>
		<th width="40%"><acme:message code="manager.workplan.tasks.description"/></th>
		<th width="20%"><acme:message code="manager.workplan.tasks.workflow"/></th>
	</tr></thead>
	<tbody>
		<jstl:forEach items="${tasks}" var="task">
			<tr class="table-light">
				<td><jstl:out value="${task.title}"/></td>
				<td><jstl:out value="${task.description}"/></td>
				<td><jstl:out value="${task.workFlow}"/></td>
			</tr>
		</jstl:forEach>
	</tbody>
</table>

 </jstl:if>