<%@ page pageEncoding="UTF-8" 
contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="product-detail" class="container py-4">
  <div class="row">
    <!-- 左：大图 + 小图预览 -->
    <div class="col-md-6">
      <div class="main-image mb-3 text-center">
        <s:if test="detailProduct.images.size() > 0">
          <s:url var="mainUrl" includeContext="true"
                 value="/public/images/%{detailProduct.images[0].imageUrl}"/>
          <img src="<s:property value='#mainUrl'/>"
               class="img-fluid"
               alt="<s:property value='detailProduct.name'/>"/>
        </s:if>
        <s:else>
          <i class="fas fa-image fa-5x text-secondary"></i>
        </s:else>
      </div>
      
    </div>

    <!-- 右：標題、價格、按鈕 -->
    <div class="col-md-6">
      <h2><s:property value="detailProduct.name"/></h2>
      <p class="h4 text-danger mb-3">$<s:property value="detailProduct.price"/></p>
      <div class="form-group w-25">
        <label for="qty">數量</label>
        <input type="number" id="qty" name="qty" value="1" min="1" class="form-control"/>
      </div>
      <button id="btn-add-cart-detail"
              class="btn btn-primary btn-block mb-2"
              data-id="<s:property value='detailProduct.id'/>">
        加入購物車
      </button>
      <button id="btn-back" class="btn btn-link">← 返回列表</button>
    </div>
  </div>
  <hr>
  <div class="description">
    <h5>商品規格</h5>
    <p><s:property value="detailProduct.description" escapeHtml="false"/></p>
  </div>

  <!-- 評價 Tab -->
  <ul class="nav nav-tabs mt-5" id="detailTab" role="tablist">
    <li class="nav-item">
      <a class="nav-link active" id="desc-tab" data-toggle="tab" href="#desc" role="tab">商品描述</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" id="review-tab" data-toggle="tab" href="#review" role="tab">
        商品評價 (<s:property value="detailProduct.reviewCount"/>)
      </a>
    </li>
  </ul>
  <div class="tab-content border p-4" id="detailTabContent">
    <div class="tab-pane fade show active" id="desc" role="tabpanel">
      <s:property value="detailProduct.description" escapeHtml="false"/>
    </div>
    <div class="tab-pane fade" id="review" role="tabpanel">
      <s:iterator value="detailProduct.reviews" status="st">
        <div class="media mb-4">
          <img src="https://via.placeholder.com/50?text=User"
               class="mr-3 rounded-circle" alt="user"/>
          <div class="media-body">
            <h6 class="mt-0">
              <s:property value="usernameMasked"/>
              <small class="text-muted ml-2">
                <s:property value="createdAt"/>
              </small>
            </h6>
            <div class="mb-2">
              <s:iterator begin="1" end="5" status="starSt">
                <i class="fas fa-star star
                  <s:if test="star >= #starSt.index">text-danger</s:if>"></i>
              </s:iterator>
            </div>
            <p><s:property value="comment" escapeHtml="false"/></p>
            <s:if test="photos.size() > 0">
              <div class="d-flex">
                <s:iterator value="photos" var="ph">
                  <img src="<s:url includeContext='true' value="%{ph.url}"/>"
                       class="img-fluid mr-2" style="max-width:100px"/>
                </s:iterator>
              </div>
            </s:if>
          </div>
        </div>
      </s:iterator>
    </div>
  </div>
</div>

<script>
// 縮圖點一下換主圖
$('.thumb-img').on('click', function(){
  var src = $(this).attr('src');
  $('.main-image img').attr('src', src);
});
// 返回列表
$('#btn-back').on('click', function(){
  $('#product-detail-container').hide();
  $('#product-list-container').show();
});
// 加入購物車
$('#btn-add-cart-detail').on('click', function(){
  var pid = $(this).data('id');
  window.location.href = '<s:url action="addToCart" namespace="/product"/>?productId=' + pid;
});
</script>
