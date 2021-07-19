<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Yanone+Kaffeesatz&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="views/css/style.css">
    <script type="text/javascript" src="views/javascript/action.js" defer></script>
    <title>Photo Blog</title>
</head>

<body>
    <nav onclick="sendReloadRequest()">Photo Slider</nav>
    <!-- Full-width images with number and caption text -->
    <div class="slideshow-container">
        
        <c:forEach items="${listImage}" var="image">
        <div class="mySlides fade">
            <div class="numbertext">Image Name: ${image.imageName}</div>
            <img src="/images/${image.imageName}"
                style="width:100%">
            <div class="text">${image.alternateText == null ? '[No caption]' : fn:escapeXml(image.alternateText)}</div>
        </div>
        </c:forEach>
        
        <!-- Next and previous buttons -->
        <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
        <a class="next" onclick="plusSlides(1)">&#10095;</a>
    </div>
    <br>

    <table id="imageTable">
        <tr>
          <th width="100px">IMAGE ID</th>
          <th>IMAGE NAME</th>
          <th>ALTERNATE TEXT</th>
        </tr>
        <c:forEach items="${listImage}" var="image">
          <tr>
            <td align="center"><a href="Javascript: Confirm.dialog('${fn:escapeXml(image.imageName)}', '${fn:escapeXml(image.alternateText)}', '${image.imageId}');">${image.imageId}</a></td>
            <td><a href="Javascript: Confirm.dialog('${fn:escapeXml(image.imageName)}', '${fn:escapeXml(image.alternateText)}', '${image.imageId}');">${fn:escapeXml(image.imageName)}</a></td>
            <td><a href="Javascript: Confirm.dialog('${fn:escapeXml(image.imageName)}', '${fn:escapeXml(image.alternateText)}', '${image.imageId}');">${image.alternateText == null ? '[No caption]' : fn:escapeXml(image.alternateText)}</a></td>
          </tr>
        </c:forEach>
    </table>
    
    
    <!-- ............. Begin vung paging ............. -->
  <c:url value="listImage.do" var="urlPaging">
    <c:param name="action" value="paging"></c:param>
    <c:param name="sortType" value="${sortType}"></c:param>
    <c:param name="sortValue" value="${sortValue}"></c:param>
    <c:param name="imageId" value="${imageId}"></c:param>
  </c:url>
      <div class="pagination">
        <c:if test="${!listPaging.isEmpty()}">
          <c:if test="${requestScope.currentPage gt LIMIT_PAGE}">
            <a href="${urlPaging}&currentPage=${listPaging[0] - LIMIT_PAGE}">&laquo;</a>&nbsp;
          </c:if>
          <c:forEach items="${listPaging}" var="page" >
          <c:choose>
            <c:when test="${currentPage eq page}">
              <a class="active" href="${urlPaging}&currentPage=${page}" >${page}</a>&nbsp;
            </c:when>
            <c:otherwise>
              <a href="${urlPaging}&currentPage=${page}">${page}</a>&nbsp;
            </c:otherwise>
          </c:choose>
          </c:forEach>
          <c:if test="${totalPage gt (listPaging[0] + LIMIT_PAGE - 1)}">
            <a href="${urlPaging}&currentPage=${listPaging[0] + LIMIT_PAGE}">&raquo;</a>
          </c:if>
        </c:if>
      </div>
  <!-- End vung paging -->
  
  <form action="editImage.do" method="post" id="formUpdate">
  <div class="confirm">
			<div></div>
			<div>
				<div id="confirmMessage"></div>
				<div id="inputField">
					<table>
					<tr>
						<td>Image Name: </td>
						<td><input type="text" id="imageName" name="imageName"/></td>
					</tr>
					<tr>
						<td>Image Alternate Text: </td>
						<td><input type="text" id="alternateText" name="alternateText"/></td>
					</tr>
					</table>
				</div>
				<div>
					<button id="confirmOk" type="button" class="btn" onclick="Confirm.ok()" > OK </button>
					<button id="confirmCancel" type="button" class="btn" onclick="Confirm.cancel()" > CANCEL </button>
				</div>
			</div>
  </div>
  </form>
  
  <form action="editImage.do" method="post" id="formUploadFile">
	<div class="confirm">
			<div></div>
			<div>
				<div id="confirmMessage"></div>
				<div id="inputField">
					<table>
					<tr>
						<td>Image Name: </td>
						<td><input type="text" id="imageName" name="imageName"/></td>
					</tr>
					<tr>
						<td>Image Alternate Text: </td>
						<td><input type="text" id="alternateText" name="alternateText"/></td>
					</tr>
					</table>
				</div>
				<div>
					<button id="confirmOk" type="button" class="btn" onclick="Confirm.ok()" > OK </button>
					<button id="confirmCancel" type="button" class="btn" onclick="Confirm.cancel()" > CANCEL </button>
				</div>
			</div>
	</div>
  </form>
</body>

</html>