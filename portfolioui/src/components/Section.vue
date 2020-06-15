<template>
  <div class="row innerbox1 innr-2"> 
                         <div class="col-md-6" style="margin:auto;">
                                        <h5>{{section.heading}}</h5>
                                        <div class="row" v-html="section.description"></div>
                                    </div>
                                <div class="col-md-6">
                                    <img width="100%" :src=section.imagePath class="img-responsive">
                                </div>
        
                    </div>                       
</template>
<script>
export default {
    props:['section']
}
</script>