<template>
  <div class="mdl-layout__container">
    <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header mdl-layout--fixed-tabs">
      <header class="mdl-layout__header portfolio-header">
        <div class="mdl-layout__header-row portfolio-logo-row">
                <span class="mdl-layout__title">
                    <span class="mdl-layout__title">TSI</span>
                </span>
        </div>

        <!--占位-->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable
                  mdl-textfield--floating-label mdl-textfield--align-right">
          <label class="pointer"></label>
          <div class="mdl-textfield__expandable-holder"><input class="mdl-textfield__input"></div>
        </div>

        <div class="mdl-layout__tab-bar mdl-js-ripple-effect">
          <a v-link="{ path: '/login' }" class="mdl-layout__tab">LOGIN</a>
          <a v-link="{ path: '/h5/index' }" class="mdl-layout__tab">HOME</a>
          <a class="mdl-layout__tab is-active">WORK</a>
        </div>
      </header>
      <main class="mdl-layout__content">
        <section class="mdl-layout__tab-panel is-active">
          <div class="page-content">
            <div class="mdl-grid">
              <div class="mdl-layout-spacer"></div>
              <div class="mdl-cell mdl-cell--5-col">
                <h3 class="mdl-typography--font-light">
                  {{ data.title }}
                </h3>
                <p class="mdl-color-text--grey-500">
                  {{ data.subtitle }}
                </p>
                <p class="mdl-color-text--grey-900 section">
                  {{ data.content }}
                </p>

                <!--老师score-->
                <div v-if="role == 0" class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label"
                     :class="{ 'mdl-badge': data.score != oldScore }" data-badge="♥">
                  <input class="mdl-textfield__input" type="text" id="score" value="init" v-model="data.score">
                  <label class="mdl-textfield__label" for="score">score</label>
                </div>
                <!--学生score-->
                <div v-if="role == 1">
                  <p class="mdl-color-text--grey-900 section pink">
                    {{ data.score }}
                  </p>
                </div>


                <!--老师-->
                <div v-if="role == 1">
                  <h5>{{ $route.params.teacher }}</h5>
                  <p class="mdl-color-text--grey-900 section">
                    {{ data.teacher_say }}
                  </p>
                </div>
                <!--学生-->
                <div v-if="role == 0">
                  <h5>student</h5>
                  <p class="mdl-color-text--grey-900 section">
                    {{ data.student_say }}
                  </p>
                </div>
                <!--老师回复-->
                <div v-if="role == 0" class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label"
                     :class="{ 'mdl-badge': data.teacher_say != oldResponse }" data-badge="♥">
                    <textarea class="mdl-textfield__input" type="text" rows="6"
                              id="response" v-model="data.teacher_say">init</textarea>
                  <label class="mdl-textfield__label" for="response">Response</label>
                </div>
                <!--学生回复-->
                <div v-if="role == 1" class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label"
                     :class="{ 'mdl-badge': data.student_say != oldComment }" data-badge="♥">
                    <textarea class="mdl-textfield__input" type="text" rows="6"
                              id="comment" v-model="data.student_say">init</textarea>
                  <label class="mdl-textfield__label" for="comment">Comment</label>
                </div>

                <!--老师保存-->
                <button v-if="role == 0" class="mdl-button mdl-js-button mdl-button--accent" @click="saveResponse">
                  Save
                </button>

                <!--学生保存-->
                <button v-if="role == 1" class="mdl-button mdl-js-button mdl-button--accent" @click="saveComment">
                  Save
                </button>

              </div>
              <div class="mdl-layout-spacer"></div>
            </div>
          </div>
        </section>
      </main>
      <div v-el:toast-save class="mdl-js-snackbar mdl-snackbar">
        <div class="mdl-snackbar__text"></div>
        <button class="mdl-snackbar__action" type="button"></button>
      </div>
      <ljun-footer></ljun-footer>
    </div>
  </div>
</template>
<style scoped>
  .mdl-textfield {
    width: 100%;
  }

  .section {
    padding: 0px 0px 2em 0px;
  }

  .portfolio-header .mdl-layout__header-row {
    padding: 0;
    -webkit-justify-content: center;
    -ms-flex-pack: center;
    justify-content: center;
  }

  .mdl-layout__title {
    font-size: 36px;
    text-align: center;
    font-weight: 300;
    color: white;
    font-weight: bold;
  }
</style>
<script>
  import ljunFooter from "./ljun-footer.vue"
  var toastData = {
    timeout: 1000,
    message: 'success'
  };
  export default{
    data() {
      return {
        role: 0,
        data: {},
        oldResponse: null,
        oldComment: null,
        oldScore: null
      }
    },
    methods: {
      getContent: function () {
        var data = {
          poId: this.$route.params.id,
          userId: 1
        }
        this.$http({
          url: '/api/po-content',
          method: 'POST',
          data: data,
          emulateJSON: true
        }).then(function (response) {
          this.data = response.data[0];
          this.data.score = this.data.score ? this.data.score : "";
          this.oldScore = this.data.score;
          this.data.teacher_say = this.data.teacher_say ? this.data.teacher_say : "";
          this.oldResponse = this.data.teacher_say;
          this.data.student_say = this.data.student_say ? this.data.student_say : "";
          this.oldComment = this.data.student_say;
          console.log(this.data);
        });
      },
      saveResponse: function () {
        var data = {
          'po_id': this.$route.params.id,
          'user_id': 1,
          'score': this.data.score,
          'teacher_say': this.data.teacher_say,
          'update_time': Date()
        }
        this.$http({
          url: '/comment/teacher',
          method: 'POST',
          data: data,
          emulateJSON: true
        }).then(function (response) {
          console.log(response);
          this.oldScore = this.data.score;
          this.oldResponse = this.data.teacher_say;
          this.$els.toastSave.MaterialSnackbar.showSnackbar(toastData);
        });
        return;
      },
      saveComment: function () {
        var data = {
          'po_id': this.$route.params.id,
          'user_id': 1,
          'student_say': this.data.student_say,
          'update_time': Date()
        }
        this.$http({
          url: '/comment/student',
          method: 'POST',
          data: data,
          emulateJSON: true
        }).then(function () {
          this.oldComment = this.data.student_say;
          this.$els.toastSave.MaterialSnackbar.showSnackbar(toastData);
        });
        return;
      }
    },
    components: {
      ljunFooter
    },
    ready: function () {
      this.getContent();
      componentHandler.upgradeAllRegistered();
    }
  }
</script>
