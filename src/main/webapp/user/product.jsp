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
	<!-- 导航栏 -->
	<nav class="navbar navbar-expand-lg navbar-dark navbar-custom">
		<div class="container">
			<a class="navbar-brand font-weight-bold" href="#">
				<i class="fas fa-store-alt mr-2"></i>沒良心網路商店
			</a>
			<s:form action="search" namespace="/product" method="get"
				cssClass="form-inline mx-auto" style="max-width: 500px;">
				<div class="input-group w-100">
					<input type="text" name="keyword" class="form-control search-box"
						placeholder="搜尋商品..." value="<s:property value='keyword'/>">
					<div class="input-group-append">
						<button class="btn btn-light" type="submit">
							<i class="fas fa-search"></i>
						</button>
					</div>
				</div>
			</s:form>
			<ul class="navbar-nav">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="userDropdown"
						role="button" data-toggle="dropdown">
						<img src="<s:property value='#session.member.avatarPath'/>"
							class="rounded-circle mr-2" width="30" height="30" alt="用戶頭像">
						<span>會員中心</span>
					</a>
					<div class="dropdown-menu dropdown-menu-right">
						<a class="dropdown-item" href="account.jsp">
							<i class="fas fa-user-circle mr-2"></i>我的帳戶
						</a>
						<a class="dropdown-item" href="orders.jsp">
							<i class="fas fa-list-alt mr-2"></i>購買清單
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item text-danger" href="logout.action">
							<i class="fas fa-sign-out-alt mr-2"></i>登出
						</a>
					</div>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="cart.jsp">
						<i class="fas fa-shopping-cart"></i>
						<span class="badge badge-light ml-1">0</span>
					</a>
				</li>
			</ul>
		</div>
	</nav>
	<!-- 主内容区 -->
	<div class="container product-container">
		<div class="row">
			<!-- 左侧筛选栏 -->
			<div class="col-lg-3 col-md-4 mb-4">
				<div class="card filter-card">
					<div class="card-body">
						<h5 class="font-weight-bold mb-4">
							<i class="fas fa-filter mr-2 text-secondary"></i>商品分類
						</h5>
						<!-- 使用 checkboxlist 自動與 action 中的 categoryIds 綁定，保持勾選狀態 -->
						<s:form id="filterForm" action="search" method="get" theme="simple">
							<div class="list-group list-group-flush">
								<s:checkboxlist 
                                    name="categoryIds"
                                    list="categoryList"
                                    listKey="id"
                                    listValue="name"
                                    theme="simple"
                                    header="false"
                                    layout="vertical"
                                    onchange="this.form.submit()"
                                />
							</div>
							<s:hidden name="keyword" value="%{keyword}" />
							<div class="mt-3">
								<a href="<s:url action='search' namespace='/product'/>"
									class="btn btn-block btn-outline-secondary">
									<i class="fas fa-times mr-2"></i>清除篩選
								</a>
							</div>
						</s:form>
					</div>
				</div>
			</div>
			<!-- 商品卡列表 -->
			<s:if test="productList != null && !productList.isEmpty()">
				<s:iterator value="productList" var="product">
					<div class="col-xl-3 col-lg-4 col-md-6 mb-4">
						<div class="card product-card h-100">
							<!-- 商品图片 -->
							<div class="card-img-container">
								<img class="card-img-top"
									src="<s:property value='#product.imagePath' default='images/default-product.png'/>"
									alt="<s:property value='#product.name'/>">
							</div>
							<!-- 商品信息 -->
							<div class="card-body">
								<!-- 商品名称 -->
								<h5 class="product-title">
									<s:property value="#product.name" />
								</h5>
								<!-- 商品价格 -->
								<div class="product-price">
									$
									<s:property value="#product.price" />
								</div>
								<!-- 商品分类 -->
								<div class="product-category">
									<i class="fas fa-tag mr-1"></i>
									<s:if test="#product.category != null">
										<a href="<s:url action='search' namespace='/product'>
                                              <s:param name='categoryIds' value='%{#product.category.id}'/>
                                           </s:url>">
											<s:property value="#product.category.name" />
										</a>
									</s:if>
									<s:else>
										<span>未分類</span>
									</s:else>
								</div>
								<!-- 加入购物车按钮 -->
								<s:form action="addToCart" namespace="/product" method="post"
									cssClass="mt-auto pt-2">
									<s:hidden name="productId" value="%{#product.id}" />
									<button type="submit" class="btn btn-add-cart">
										<i class="fas fa-cart-plus mr-2"></i>加入購物車
									</button>
								</s:form>
							</div>
						</div>
					</div>
				</s:iterator>
			</s:if>
			<!-- 分頁 -->
			<div class="col-12 mt-5">
				<nav>
					<ul class="pagination justify-content-center">
						<s:iterator begin="1" end="totalPages" var="page">
							<li class="page-item <s:if test='%{#page == pageNo}'>active</s:if>">
								<s:url var="pageUrl" action="search">
									<s:param name="keyword" value="keyword" />
                                    <!-- 將已選取的分類逐一加入 URL 參數 -->
                                    <s:iterator value="categoryIds" var="catId">
                                        <s:param name="categoryIds" value="%{#catId}" />
                                    </s:iterator>
									<s:param name="pageNo" value="%{#page}" />
								</s:url>
								<a class="page-link" href="<s:property value='#pageUrl'/>">
									<s:property value="#page" />
								</a>
							</li>
						</s:iterator>
					</ul>
				</nav>
			</div>
			<s:else>
				<!-- 无商品显示 -->
				<div class="col-12 text-center py-5">
					<i class="fas fa-box-open fa-4x text-muted mb-4"></i>
					<h3 class="text-muted mb-3">暫無商品數據</h3>
					<s:if test="keyword != null || (categoryIds != null && !categoryIds.isEmpty())">
						<a href="<s:url action='search' namespace='/product'/>"
							class="btn btn-primary mt-3">
							<i class="fas fa-times-circle mr-2"></i>清除搜尋條件
						</a>
					</s:if>
				</div>
			</s:else>
		</div>
	</div>
	<!-- JavaScript  -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
		// 页面加载完成后执行
		$(document).ready(function() {
			// 确保卡片高度一致
			equalizeCardHeights();
			// 窗口大小改变时重新计算
			$(window).resize(function() {
				equalizeCardHeights();
			});
		});
		function equalizeCardHeights() {
			// 重置所有卡片高度
			$('.product-card').css('height', 'auto');
			// 计算每行卡片的最大高度
			$('.row').each(function() {
				var maxHeight = 0;
				var $cards = $(this).find('.product-card');
				$cards.each(function() {
					var thisHeight = $(this).outerHeight();
					if (thisHeight > maxHeight) {
						maxHeight = thisHeight;
					}
				});
				// 应用最大高度
				$cards.height(maxHeight);
			});
		}
	</script>
</body>
</html>
