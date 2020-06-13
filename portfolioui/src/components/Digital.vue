<template>
    <div id="app">
        <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
        <hr>
        <h4>The below is the data in v-model , this text can be stored directly in database</h4>
        <div>{{editorData}}</div>
    </div>
</template>
<script>
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
export default {
  name: 'app',
  data () {
    return {
      editor: ClassicEditor,
      editorData: '<p>Content of the editor.</p>',
      editorConfig: {
        toolbar: ['bold', 'italic', 'bulletedList', 'numberedList']
      }
    }
  }
}
</script>
