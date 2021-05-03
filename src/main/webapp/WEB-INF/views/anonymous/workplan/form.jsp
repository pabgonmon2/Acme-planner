<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox  code="anonymous.workplan.list.label.startDate" path="startDate" />
	<acme:form-textbox  code="anonymous.workplan.list.label.endDate" path="endDate"/>
	<acme:form-textbox  code="anonymous.workplan.list.label.workLoad" path="workLoad" />
	<acme:form-textbox  code="anonymous.workplan.list.label.publicPlan" path="publicPlan" />
	
	<acme:form-return code="anonymous.provider.form.button.return"/>
</acme:form>
