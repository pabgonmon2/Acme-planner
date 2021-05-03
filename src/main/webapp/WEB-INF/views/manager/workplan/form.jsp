<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
 	
 <jstl:if test="${canUpdate}">
	<acme:form-moment code="manager.workplan.form.label.startDate" path="startDate"/>
	<jstl:if test="${command!='create' }">
	<acme:message code="manager.workplan.message.recommend"/>
	<acme:message code= "${startRecommend}"/>
</jstl:if>
</jstl:if>
 <jstl:if test="${!canUpdate}">
	<acme:form-moment readonly="true" code="manager.workplan.form.label.startDate" path="startDate"/>
</jstl:if>

 <jstl:if test="${canUpdate}">
	<acme:form-moment  code="manager.workplan.form.label.endDate" path="endDate"/>
<jstl:if test="${command!='create' }">
	<acme:message code="manager.workplan.message.recommend"/>
	<acme:message code= "${finalRecommend}"/>
</jstl:if>
</jstl:if>
<jstl:if test="${!canUpdate}">
	<acme:form-moment  readonly="true" code="manager.workplan.form.label.endDate" path="endDate"/>
</jstl:if>
<jstl:if test="${command!='create' }">
	<acme:form-double readonly="true" code="manager.workplan.form.label.workLoad" path="workLoad"/>
	<acme:form-checkbox readonly="true" code="manager.workplan.form.label.publicPlan" path="publicPlan"/>
</jstl:if>
	<jstl:if test="${command=='create' }">
	<acme:form-submit code="manager.workplan.form.button.create" action="/manager/workplan/create"/>
	</jstl:if>
	 <jstl:if test="${command!='create' }">
		<acme:form-submit code="manager.workplan.form.button.delete" action="/manager/workplan/delete"/>
		 <jstl:if test="${canUpdate}"><acme:form-submit code="manager.workplan.form.button.update" action="/manager/workplan/update"/></jstl:if>
		<acme:form-submit code="manager.workplan.form.button.publish" action="/manager/workplan/publish"/>
	</jstl:if>
	<acme:form-return  code= "manager.workplan.form.button.return"/>
</acme:form>

<br>

<jstl:if test="${command!='create' && tasks.size()>0 }">
	<table class="table table-striped table-condensed table-hover nowrap w-100"> 
		<thead><tr>
			<th width="20%"><acme:message code="manager.workplan.tasks.title"/></th>
			<th width="40%"><acme:message code="manager.workplan.tasks.description"/></th>
			<th width="20%"><acme:message code="manager.workplan.tasks.workflow"/></th>
			<th></th>
		</tr></thead>
		<tbody>
			<jstl:forEach items="${tasks}" var="task">
				<tr class="table-light">
					<td><jstl:out value="${task.title}"/></td>
					<td><jstl:out value="${task.description}"/></td>
					<td><jstl:out value="${task.workFlow}"/></td>
					 <jstl:if test="${canUpdate}"><td><acme:form>
						<input type="hidden" name="deleteTask" value="${task.id}"/>
						<acme:form-hidden path="id"/>
						<acme:form-submit code="manager.workplan.form.button.delete" action="/manager/workplan/delete_task"/>
					</acme:form></td></jstl:if>
				</tr>
			</jstl:forEach>
		</tbody>
	</table>
 </jstl:if>
 
 	 <jstl:if test="${command!='create' && canUpdate}">
	<acme:form>
		<acme:form-select code="manager.workplan.form.addTask" path="addTask">
			<jstl:forEach items="${tasksInsert}" var="task">
				<acme:form-option code="${task.title}" value="${task.id}"/>
			</jstl:forEach>
		</acme:form-select>
		<acme:form-submit code="manager.workplan.form.button.addTask" action="/manager/workplan/add_task"/>
	</acme:form>
	</jstl:if>



