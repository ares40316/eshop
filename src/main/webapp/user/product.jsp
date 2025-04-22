<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<title>商品列表 - 沒良心網路商店</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<style>
:root {
	--primary-color: #2c3e50;
	--secondary-color: #e74c3c;
	--light-gray: #f8f9fa;
}

body {
	background-color: var(--light-gray);
	font-family: "Microsoft JhengHei", sans-serif;
}

.navbar-custom {
	background-color: var(--primary-color);
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.product-container {
	margin-top: 2rem;
}

/* 商品卡片样式 */
.product-card {
	border: none;
	border-radius: 10px;
	overflow: hidden;
	transition: all 0.3s ease;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	margin-bottom: 25px;
	height: 100%;
	background: white;
}

.product-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
}

.card-img-container {
	height: 200px;
	background: #f5f5f5;
	display: flex;
	align-items: center;
	justify-content: center;
	padding: 20px;
	border-bottom: 1px solid #eee;
}

.card-img-top {
	max-height: 100%;
	max-width: 100%;
	object-fit: contain;
}

.card-body {
	padding: 1.5rem;
	display: flex;
	flex-direction: column;
}

.product-title {
	font-size: 1.1rem;
	font-weight: 600;
	margin-bottom: 0.75rem;
	color: var(--primary-color);
	height: 3em;
	overflow: hidden;
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
	line-height: 1.5;
}

.product-price {
	font-size: 1.3rem;
	font-weight: bold;
	color: var(--secondary-color);
	margin: 0.5rem 0;
}

.product-category {
	font-size: 0.9rem;
	color: #6c757d;
	margin-bottom: 1rem;
}

.product-category a {
	color: inherit;
	text-decoration: none;
	transition: color 0.2s;
}

.product-category a:hover {
	color: var(--primary-color);
	text-decoration: underline;
}

.btn-add-cart {
	border-radius: 25px;
	padding: 8px 20px;
	font-size: 0.95rem;
	width: 100%;
	background-color: var(--secondary-color);
	border: none;
	margin-top: auto;
	transition: all 0.3s;
}

.btn-add-cart:hover {
	background-color: #c0392b;
	transform: translateY(-2px);
}

.search-box {
	border-radius: 20px;
	padding: 8px 15px;
}

/* 响应式调整 */
@media ( max-width : 768px) {
	.card-img-container {
		height: 160px;
		padding: 15px;
	}
	.card-body {
		padding: 1.25rem;
	}
}
</style>
</head>
<body>
	<!-- 頂部導航 + 搜索 -->
	<nav class="navbar navbar-expand-lg navbar-dark navbar-custom">
		<div class="container">
			<a class="navbar-brand" href="#"><i class="fas fa-store-alt mr-2"></i>沒良心網路商店</a>
			<!-- 搜索框 -->
			<s:form action="search" namespace="/product" method="get"
				cssClass="form-inline mx-auto" style="width:400px;">
				<div class="input-group w-100">
					<input type="text" name="keyword" class="form-control"
						placeholder="搜尋商品…" value="<s:property value='keyword'/>" />
					<div class="input-group-append">
						<button class="btn btn-light" type="submit">
							<i class="fas fa-search"></i>
						</button>
					</div>
				</div>
			</s:form>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="cart.jsp"><i
						class="fas fa-shopping-cart"></i> <span class="badge badge-light">0</span>
				</a></li>
			</ul>
		</div>
	</nav>

	<div class="container my-4">
		<!-- 整个搜索+筛选+分页都在一个表单里 -->
		<s:form action="search" namespace="/product" method="get">
			<div class="row">
				<!-- 左侧 分类 -->
				<div class="col-md-3">
					<h5>分類篩選</h5>
					<s:iterator value="categoryList" var="cat">
						<div class="form-check">
							<input class="form-check-input" type="checkbox"
								name="categoryIds" id="cat_<s:property value='#cat.id'/>"
								value="<s:property value='#cat.id'/>"
								<s:if test="categoryIds.contains(#cat.id.toString())">checked</s:if>
								onchange="this.form.submit();" /> <label
								class="form-check-label" for="cat_<s:property value='#cat.id'/>">
								<s:property value="#cat.name" />
							</label>
						</div>
					</s:iterator>
					<!-- 保留當前搜索詞 -->
					<input type="hidden" name="keyword"
						value="<s:property value='keyword'/>" />
				</div>

				<!-- 右侧 商品列表 -->
				<div class="col-md-9">
					<div class="row">
						<s:iterator value="productList" status="prod">
							<div class="col-lg-4 col-md-6 mb-4">
								<div class="card product-card">
									<div class="card-img-container">
										<!-- 先檢查 prod.images 是否有東西 -->
										<s:if test="%{images != null && images.size() > 0}">
											<s:set var="firstImg" value="%{images.get(0).imageUrl}" />
											<s:url var="imgUrl" includeContext="true"
												value="/public/images/%{firstImg}" />
											<img src="${imgUrl}" class="card-img-top" alt="%{name}" />
										</s:if>
										<s:else>
											<i class="fas fa-image fa-3x text-secondary"></i>
										</s:else>
									</div>
									<div class="card-body">
										<h5 class="card-title">
											<s:property value="name" />
										</h5>
										<p class="card-text text-truncate">
											<s:property value="description" />
										</p>
										<p class="card-text text-danger">
											$
											<s:property value="price" />
										</p>
										<a
											href="<s:url action='detail' namespace='/product'><s:param name='id'><s:property value='id'/></s:param></s:url>"
											class="btn btn-sm btn-primary">詳細</a> <a
											href="<s:url action='addToCart' namespace='/product'><s:param name='productId'><s:property value='id'/></s:param></s:url>"
											class="btn btn-sm btn-secondary">加入購物車</a>
									</div>
								</div>
							</div>
						</s:iterator>
					</div>

					<!-- 分页 -->
					<nav aria-label="Page navigation">
						<ul class="pagination justify-content-center">
							<li class="page-item <s:if test='pageNo<=1'>disabled</s:if>">
								<a class="page-link"
								href="<s:url action='search' namespace='/product'>
                           <s:param name='pageNo'><s:property value='pageNo-1'/></s:param>
                           <s:param name='keyword'><s:property value='keyword'/></s:param>
                           <s:iterator value='categoryIds' var='cid'>
                             <s:param name='categoryIds'><s:property value='#cid'/></s:param>
                           </s:iterator>
                         </s:url>">
									上一頁 </a>
							</li>

							<s:iterator begin="1" end="%{totalPages}" status="stat">
								<li
									class="page-item <s:if test='#stat.index+1==pageNo'>active</s:if>">
									<a class="page-link"
									href="<s:url action='search' namespace='/product'>
                             <s:param name='pageNo'><s:property value='#stat.index+1'/></s:param>
                             <s:param name='keyword'><s:property value='keyword'/></s:param>
                             <s:iterator value='categoryIds' var='cid2'>
                               <s:param name='categoryIds'><s:property value='#cid2'/></s:param>
                             </s:iterator>
                           </s:url>">
										<s:property value="#stat.index+1" />
								</a>
								</li>
							</s:iterator>

							<li
								class="page-item <s:if test='pageNo>=totalPages'>disabled</s:if>">
								<a class="page-link"
								href="<s:url action='search' namespace='/product'>
                           <s:param name='pageNo'><s:property value='pageNo+1'/></s:param>
                           <s:param name='keyword'><s:property value='keyword'/></s:param>
                           <s:iterator value='categoryIds' var='cid3'>
                             <s:param name='categoryIds'><s:property value='#cid3'/></s:param>
                           </s:iterator>
                         </s:url>">
									下一頁 </a>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</s:form>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
		$(window).on('load resize', function() {
			$('.col-md-9 > .row').each(function() {
				var maxH = 0;
				var $cards = $(this).find('.product-card');
				$cards.height('height', 'auto').each(function() {
					maxH = Math.max(maxH, $(this).outerHeight());
				});
				$cards.height(maxH);
			});
		});
	</script>
</body>
</html>
