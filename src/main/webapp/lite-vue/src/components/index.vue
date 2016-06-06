<template>
  <div class="mdl-layout__container">
    <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header mdl-layout--fixed-tabs">
      <header class="mdl-layout__header portfolio-header">
        <div class="mdl-layout__header-row portfolio-logo-row">
                <span class="mdl-layout__title">
                    <span class="mdl-layout__title">TSI</span>
                </span>
        </div>
        <div class="mdl-layout-spacer"></div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable
                  mdl-textfield--floating-label mdl-textfield--align-right">
          <label class="pointer" for="waterfall-exp">SEARCH</label>
          <label class="mdl-button mdl-js-button mdl-button--icon"
                 for="waterfall-exp">
            <i class="material-icons">search</i>
          </label>
          <div class="mdl-textfield__expandable-holder">
            <input class="mdl-textfield__input" type="text" name="sample"
                   id="waterfall-exp" v-model="search">
          </div>
        </div>

        <div class="mdl-layout__tab-bar mdl-js-ripple-effect">
          <a v-link="{ path: '/login' }" class="mdl-layout__tab">LOGIN</a>
          <a v-link="{ path: '/h5/index' }" class="mdl-layout__tab  is-active">HOME</a>
        </div>
      </header>
      <main class="mdl-layout__content">
        <section class="mdl-layout__tab-panel is-active" id="work">
          <div class="page-content">
            <div class="mdl-grid">
              <div class="mdl-layout-spacer"></div>
              <table class="mdl-cell mdl-cell--8-col mdl-data-table mdl-js-data-table mdl-shadow--2dp">
                <thead>
                <tr>
                  <th class="mdl-data-table__cell--non-numeric">Teacher</th>
                  <th>Work</th>
                  <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="task in tasks | filterBy search in 'teacher' 'work' | orderBy id -1 | limitBy 20'"
                    v-link="{ path: '/h5/work/' + task.id }">
                  <td class="mdl-data-table__cell--non-numeric">{{ task.teacher }}</td>
                  <td>{{ task.name }}</td>
                  <td>{{ task.score }}</td>
                </tr>
                </tbody>
              </table>
              <div class="mdl-layout-spacer"></div>
            </div>
          </div>
        </section>
      </main>
      <ljun-footer></ljun-footer>
    </div>
  </div>
</template>
<style scoped>
  .pointer {
    cursor: pointer;
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
  export default{
    data()
    {
      return {
        search: '',
        tasks: []
      }
    },
    methods: {
      poList: function () {
        this.$http({
          url: '/api/po-list',
          method: 'POST',
          emulateJSON: true
        }).then(function (response) {
          console.log(response.data);
          this.tasks = response.data;
        });
      }
    },
    components: {
      ljunFooter
    },
    ready: function () {
      componentHandler.upgradeAllRegistered();
      this.poList();
    }
  }
</script>
