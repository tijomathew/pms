<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 25/6/15
  Time: 10:48 AM
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="date" class="java.util.Date" />
<fmt:formatDate value="${date}" pattern="yyyy" var="currentYear" />
<footer role="contentinfo" style="background-color: #325263">
  <div class="clearfix">
    <ul class="list-unstyled list-inline pull-left">
      <li style="font-size: 96%;
    font-weight: bold;
    color: #fff;">PMS &copy; Shinto Jacob, Naduvilekuttu ${currentYear}</li>
    </ul>
    <button class="pull-right btn btn-inverse-alt btn-xs hidden-print" id="back-to-top">
      <i class="fa fa-arrow-up"></i>
    </button>
  </div>
</footer>
