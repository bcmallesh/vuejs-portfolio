<template>

    <div class="portfolio col-lg-3 col-md-6 " v-bind:class="[design.industry , design.work , design.type ]">
                                    <figure class="portfolio-image">
                                        <img :src="require('@/assets/' + design.image + '')" alt="CxCalibration" class="img-responsive">
                                        <figcaption class="caption">
                                            <div class="caption-content">
                                                <h3 class="portfolio-item-title text-center sub-title">{{design.name}}</h3>
                                                <p>{{design.description | design.id}}</p>
                                            </div>
                                        </figcaption>
                                    </figure>
                                </div>
</template>
<script>
export default {
  props : ['design']
}
</script>

<style>
:root {
  --main-color:#7028B7;
}

.modal-dialog,
.modal-content {
  /* 80% of window height */
  height: 80%;
}

.modal-body {
  /* 100% = dialog height, 120px = header + footer */
  max-height: calc(100% - 120px);
  overflow-y: scroll;
}

body {
  font-family: Regular;
  background: rgba(255, 0, 0, 0) !important;
}

.navbar-fixed-top.scrolled {
  background-color: #fff !important;
  -webkit-transition: background-color 200ms linear;
  transition: background-color 200ms linear;
}

.navbar-fixed-top.scrolled .evry-logo {
  fill: black !important;
}

.navbar-fixed-top.scrolled span {
  color: black !important;
}

.navbar-fixed-top.scrolled .nav-link {
  color: #555;
}

.background_dig {
  background: url(../assets/images/img.jpg);
  width: 100%;
  position: relative;
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-size: cover;
}

.navbar {
  padding-top: 20px;
}

.text-center {
  text-align: center;
}

#header {
  color: white;
}

#header a span {
  color: white;
  font-size: 1.4em;
}

#header a span::before {
  content: " ";
  background: #d9a4f5;
  opacity: 0.5;
  width: 1px;
  padding-right: 1px;
  margin-right: 10px;
  margin-left: 7px;
}

#header .padding1x {
  padding-top: 84px;
}

#header .padding2x {
  padding-top: 22px;
}

#header .content h1 {
  font-size: 3em;
  font-family: Regular-bold;
  text-shadow: #898787b3 1px 1px 3px;
  margin-bottom: 0px;
}

#header .content h2 {
  font-size: 2em;
  font-family: Regular-bold;
}

#header .content .btn {
  background: #ffffff3b;
  border-radius: 100px;
  font-size: 1.2em;
  margin-top: 20px;
}

#header .content .btn a {
  text-decoration: none;
  color: white;
  font-family: Regular-bold;
  padding-right: 10px;
  padding-left: 10px;
}

#header .content .btn a svg {
  fill: white;
  margin-left: 11px;
}

#header .content .btn:hover {
  background: white;
}

#header .content .btn:hover a {
  color: var(--main-color);
}

#header .content .btn:hover a svg {
  fill: var(--main-color);
}

#header .evry-logo {
  fill: white;
  height: 42px;
  margin-top: -4px;
}

.container-box {
  position: absolute;
  top: 60%;
}

.main-box {
  background: white;
  border-radius: 20px;
  margin-top: 80px !important;
  padding-right: 35px;
  padding-bottom: 42px;
  padding-left: 35px;
  -webkit-transition: 300ms all cubic-bezier(0.4, 0, 0.2, 1);
  transition: 300ms all cubic-bezier(0.4, 0, 0.2, 1);
  margin-top: 24px;
  -webkit-box-shadow: 0 8px 60px 0 rgba(103, 151, 255, 0.11), 0 12px 90px 0 rgba(103, 151, 255, 0.11);
          box-shadow: 0 8px 60px 0 rgba(103, 151, 255, 0.11), 0 12px 90px 0 rgba(103, 151, 255, 0.11);
}

.main-box .filter {
  margin-top: 30px;
}

.main-box h2 {
  text-align: center;
  color: var(--main-color);
  font-family: regular-bold;
  padding: 20px;
  font-size: 2.2em;
  padding-top: 35px;
  padding-bottom: 55px;
}

#boxinner h3 {
  text-align: center;
  color: #000;
  font-family: regular-bold;
  padding: 20px;
  font-size: 2.2em;
  padding-top: 35px;
  padding-bottom: 55px;
}
/*
====================================================
				6.Portfolio
====================================================
*/
.portfolio {
  padding: 10px 15px;
}

.portfolio-image {
  position: relative;
  overflow: hidden;
  border-radius: 5px;
}

.portfolio-image img {
  width: 100%;
  height: 100%;
  -webkit-transform: scale(1);
          transform: scale(1);
  -webkit-transition: all 0.9s ease;
  transition: all 0.9s ease;
  position: relative;
}

.portfolio-image:hover img {
  -webkit-transform: scale(1.3);
          transform: scale(1.3);
  cursor: pointer;
}

.portfolio-image::after {
  content: '';
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0px;
  left: 0px;
}

.work .caption {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  cursor: pointer;
  opacity: 0;
  -webkit-transform: scale(1.2);
          transform: scale(1.2);
  -webkit-transition: all 0.5s ease;
  transition: all 0.5s ease;
  z-index: 999;
}

.portfolio-item-title {
  color: #fff;
  font-size: 1.2rem;
  font-weight: 500;
  margin-bottom: 5px;
  letter-spacing: 1px;
  font-family: regular-bold;
}

.caption-content {
  width: 100%;
  height: 100%;
  position: relative;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: center;
      -ms-flex-pack: center;
          justify-content: center;
  -webkit-box-align: center;
      -ms-flex-align: center;
          align-items: center;
  width: 100%;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
      -ms-flex-direction: column;
          flex-direction: column;
  color: #fff !important;
  padding: 20px;
}

.caption-content p {
  color: #fff;
  font-size: 1rem;
  text-shadow: 3px -1px 5px rgba(0, 40, 88, 0.72);
  line-height: 16px;
}

.portfolio-image:hover .caption {
  opacity: 1;
  -webkit-transform: scale(1);
          transform: scale(1);
}

.caption-content {
  width: 100%;
  height: 100%;
  text-align: center;
  background-color: rgba(1, 93, 199, 0.4);
  background-image: -webkit-gradient(linear, left top, right top, from(rgba(1, 93, 199, 0.75)), to(rgba(162, 0, 255, 0.75)));
  background-image: linear-gradient(left, rgba(1, 93, 199, 0.75), rgba(162, 0, 255, 0.75));
  filter: progid:DXImageTransform.Microsoft.gradient(startColorStr='rgba(1, 93, 199, 0.4)', endColorStr='rgba(162, 0, 255, 0.4)', gradientType='1');
  position: relative;
}

#return-to-top {
  position: fixed;
  bottom: 20px;
  right: 20px;
  z-index: 99999;
  width: 50px;
  height: 50px;
  display: block;
  text-decoration: none;
  border-radius: 35px;
  display: none;
  -webkit-transition: all 0.3s linear;
  -webkit-transition: all 0.3s ease;
  transition: all 0.3s ease;
}

#return-to-top i {
  margin: 0;
  position: relative;
  left: -2px;
  top: 3px;
  font-size: 25px;
  -webkit-transition: all 0.3s ease;
  transition: all 0.3s ease;
}

#return-to-top:hover i {
  top: -3px;
}

.close-icon {
  display: inline-block;
  width: 24px;
  height: 24px;
  background-image: url(../assets/images/close-icon.png);
}

.center {
  margin: auto;
  text-align: left;
}

.portfolios {
  margin-top: 15px;
}

.filtericon {
  background: url(../assets/images/SVG/filter-01.svg);
  background-repeat: no-repeat;
  width: 158px;
  height: 130px;
  position: absolute;
  margin-top: -39px;
  margin-left: 3px;
  background-repeat: no-repeat;
  -webkit-transition-timing-function: ease-in;
  transition-timing-function: ease-in;
  -webkit-transition: 0.2s;
  transition: 0.2s;
}

.filtericon:hover {
  background: url(../assets/images/SVG/filter-hover-01.svg);
  background-repeat: no-repeat;
}

.modal-overlay {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  background-color: rgba(0, 0, 0, 0.4);
  opacity: 0;
  z-index: 8999;
  transition: all 0.5s ease;
  -webkit-transition: all 0.5s ease;
}

.show-modal-overlay {
  opacity: 1;
  position: fixed;
  transition: all 0.5s ease;
  -webkit-transition: all 0.5s ease;
}

.filter-modal {
  padding: 15px;
  background-color: white;
  width: 520px;
  top: -203px;
  left: -471px;
  position: absolute;
  visibility: visible;
  height: auto;
  z-index: 9999;
  border-radius: 5px;
  font-family: 'Regular', sans-serif;
  opacity: 0;
  visibility: hidden;
  -webkit-box-shadow: 2px 2px 8px 1px rgba(0, 0, 0, 0.2);
  box-shadow: 2px 2px 8px 1px rgba(0, 0, 0, 0.2);
  transition: all 0.5s ease;
  -webkit-transition: all 0.5s ease;
}

.filter-modal.show-panel {
  right: 80px;
  opacity: 1;
  visibility: visible;
  overflow: visible;
  transition: all 0.5s ease;
  -webkit-transition: all 0.5s ease;
}

.filter-modal:before {
  content: "";
  width: 0;
  height: 0;
  border-top: 10px solid transparent;
  border-bottom: 10px solid transparent;
  border-left: 10px solid #fff;
  position: absolute;
  right: -10px;
  top: 49%;
}

.filter-modal-body {
  margin-top: 10px;
  text-align: left;
}

.filter-modal-body h3 {
  text-align: left;
  color: #000;
  font-family: regular-bold;
  padding: 0px;
  font-size: 2.2em;
  padding-top: 0px;
  font-size: 1.3em;
  text-align: left;
  padding-bottom: px;
}

.text-left {
  text-align: left;
  float: left;
}

.text-right {
  text-align: right;
  float: right;
}

.filter-modal .filter-close {
  display: block;
  cursor: pointer;
  position: absolute;
  top: 12px;
  right: 13px;
  /* padding: 17px; */
  /* opacity: .8; */
  font-size: 1.3rem;
  z-index: 999999;
}

.form-group-checkbox {
  display: block;
  margin-bottom: 5px;
}

.form-group-checkbox input {
  padding: 0;
  height: initial;
  width: initial;
  margin-bottom: 0;
  display: none;
  cursor: pointer;
}

.form-group-checkbox label {
  position: relative;
  cursor: pointer;
  /* font-size: 0.8rem; */
  font-family: regular;
  color: #000;
  font-size: 16px;
}

.form-group-checkbox label:before {
  content: '';
  -webkit-appearance: none;
  background-color: transparent;
  border: 1px solid #dadada;
  /* -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05); */
  /* box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05); */
  padding: 7px;
  display: inline-block;
  position: relative;
  vertical-align: text-bottom;
  cursor: pointer;
  margin-right: 10px;
  border-radius: 0px;
}

.form-group-checkbox input:checked + label:after {
  content: '';
  display: block;
  position: absolute;
  top: 6px;
  left: 5px;
  width: 6px;
  height: 12px;
  border: solid #6d2cb8;
  border-width: 0 2px 2px 0;
  -webkit-transform: rotate(45deg);
  transform: rotate(45deg);
}

.filter-modal-footer {
  border-top: 1px solid #eee;
  padding-top: 16px;
  margin-top: 5px;
}

.btn-primary {
  color: #fff;
  background-color: #7d28b2;
  border-color: #7d28b2;
  border-radius: 46px;
}

.btn-light {
  color: #212529;
  background-color: #f8f9fa;
  border-color: #f8f9fa;
  border-radius: 42px;
}

.foot {
  margin-top: 100px;
}

footer {
  padding: 12px 0;
  background-color: #D9D9DB;
  bottom: 0;
  width: 100%;
}

.copyright p {
  font-size: 0.85rem;
  color: #8E8E8F;
  margin-bottom: 0px;
}

.copyright span {
  color: #8E8E8F;
  font-size: 16px;
}

.innermk {
  background: #692881;
  background: linear-gradient(184deg, #692881 13%, rgba(255, 255, 255, 0) 50%);
  position: absolute;
  top: 0;
  left: 0;
  z-index: 0;
  height: 70%;
  width: 100%;
}

.inner {
  background: white;
  background: -webkit-gradient(linear, left bottom, left top, color-stop(78%, white), color-stop(78%, #7028B7));
  background: linear-gradient(0deg, white 78%, #7028B7 78%);
  height: 100%;
  width: 100%;
}

.inner2 {
  background: white;
  background: -webkit-gradient(linear, left bottom, left top, color-stop(84%, white), color-stop(84%, #7028B7));
  background: linear-gradient(0deg, white 84%, #7028B7 84%);
  height: 25%;
  width: 100%;
}

#boxinner {
  padding-left: 35px;
  padding-right: 35px;
}

#boxinner .card {
  background: none;
}

#boxinner .card h5 {
  font-family: regular-bold;
  margin-left: 37px;
  color: #000;
}

#boxinner .card ul li {
  margin-left: 13px;
  list-style: none;
  color: #000;
}

#boxinner .card ul li::before {
  content: "\2022";
  color: #d1d1d1;
  font-weight: bold;
  display: inline-block;
  width: 1em;
  margin-left: -1em;
}

#boxinner .card img {
  width: 259px;
  margin-top: -83px;
}

.cardone {
  border-right: 1px solid rgba(253, 209, 209, 0.356);
  border-left: 0px;
  border-top: 0px;
  border-bottom: 0px;
  border-radius: 0px;
}

.cardone .cardoneimg {
  background: url(../assets/images/SVG/img2-01.svg);
  width: 300px;
  height: 300px;
  margin-top: -83px;
  background-repeat: no-repeat;
}

.cardtwo {
  border-bottom: 1px solid rgba(253, 209, 209, 0.356);
  border-left: 0px;
  border-top: 0px;
  border-right: 0px;
  border-radius: 0px;
}

.cardtwo .cardtwoimg {
  background: url(../assets/images/SVG/uxdesign.svg);
  width: 300px;
  height: 300px;
  margin-top: -83px;
  background-repeat: no-repeat;
}

.cardthree {
  border-top: 1px solid rgba(224, 224, 224, 0.356);
  border-left: 0px;
  border-bottom: 0px;
  border-right: 0;
  border-radius: 0px;
  padding-top: 47px;
}

.cardfour {
  border-top: 0px;
  border-left: 1px solid rgba(224, 224, 224, 0.356);
  border-bottom: 0px;
  border-right: 0;
  border-radius: 0px;
  padding-top: 47px;
}

.offer {
  background-image: url(../assets/images/SVG/triangle.svg);
  background-position: 0% 98%;
  background-repeat: no-repeat, repeat;
  justify-items: left;
}

.design {
  background-image: url(../assets/images/SVG/footer.svg);
  background-position: 0% 107%;
  background-repeat: no-repeat, repeat;
  justify-items: right;
  /* margin: auto; */
  text-align: center;
  /* margin-top: 5px; */
  padding-bottom: 104px;
}

.design h3 {
  text-align: center;
  color: #000;
  font-family: regular-bold;
  padding: 20px;
  font-size: 2.2em;
  padding-top: 100px;
  padding-bottom: 55px;
}

.design .boxtwo {
  -webkit-transition: 300ms all cubic-bezier(0.4, 0, 0.2, 1);
  transition: 300ms all cubic-bezier(0.4, 0, 0.2, 1);
  margin-top: 24px;
  -webkit-box-shadow: 0 8px 60px 0 rgba(103, 151, 255, 0.11), 0 12px 90px 0 rgba(103, 151, 255, 0.11);
          box-shadow: 0 8px 60px 0 rgba(103, 151, 255, 0.11), 0 12px 90px 0 rgba(103, 151, 255, 0.11);
  background: white;
  border-radius: 10px;
  padding: 17px;
  margin-right: 10px;
  margin-left: 10px;
}

.design .boxtwo img {
  width: 55px;
  position: absolute;
  top: -35px;
  /* float: left; */
  /* text-align: left; */
  right: 136px;
}

.design .boxtwo h5 {
  text-align: center;
  text-align: center;
  font-family: regular-bold;
  color: #000;
}

.design .boxtwo h6 {
  text-align: left;
  font-family: regular-bold;
  padding-top: 30px;
}

.design .boxtwo ul:nth-of-type(1) li:nth-of-type(1) {
  padding-top: 20px;
}

.design ul {
  padding-left: 0px;
  text-align: left;
}

.design ul li {
  list-style: none;
  color: #000;
}

.innerbox1 h5 {
  padding-top: 21px;
  padding-bottom: 16px;
  font-size: 25px;
  font-family: regular-bold;
  font-size: 20px;
  color: #000;
}

.innerbox1 h6 {
  font-family: regular-bold;
  padding-top: 25px;
  font-size: 20px;
  color: #000;
}

.innerbox1 ul {
  padding-left: 15px;
  padding-bottom: 25px;
}

.innerbox1 ul li {
  line-height: 28px;
}

.imgq {
  position: absolute;
  z-index: 2;
  width: 90%;
  left: 7%;
  top: -118%;
}

.boxsm {
  display: -webkit-inline-box;
  display: -ms-inline-flexbox;
  display: inline-flex;
  margin-left: 16px;
}

.boxsm .box2 {
  border: 1px solid #ece9e9;
  padding: 10px;
  padding-left: 10px;
  padding-left: 20px;
  padding-right: 20px;
  margin-left: 11px;
  border-radius: 30px;
}

.boxsm .box1 {
  border: 1px solid #ece9e9;
  padding: 10px;
  padding-left: 20px;
  padding-right: 20px;
  border-radius: 30px;
}

.boxsm img {
  height: 25px;
  width: 25px;
  margin-right: 11px;
}

.mobile-frame-section {
  width: 315px;
  margin: 0 auto;
  position: relative;
}

.mobile-frame-section img {
  width: 315px;
}

.mobile-frame-items {
  position: absolute;
  width: 248px;
  left: 31px;
  top: 97px;
}

.mobile-frame-items img {
  width: 247px;
}

.height1x {
  height: 100px;
  background: red;
}

.innr-2 {
  padding-top: 49px;
  padding-bottom: 49px;
}

.innr3 {
  padding-top: 49px;
  padding-bottom: 49px;
  background: #f5f5f58f;
}

.innr3 h5 {
  font-family: regular-bold;
  padding-top: 46px;
  font-size: 20px;
  color: #000;
}

.innr-1 {
  padding-top: 49px;
  padding-bottom: 49px;
  background: #f5f5f58f;
  margin-top: 30px;
}

.innr4 {
  padding-top: 49px;
  padding-bottom: 49px;
}

.innr4 h5 {
  font-family: regular-bold;
  padding-top: 46px;
  font-size: 20px;
  color: #000;
}

.img-blk {
  padding: 25px;
}

#filterClearBtn i {
  padding-right: 10px;
}

#filterApplyBtn i {
  padding-right: 10px;
}
</style>