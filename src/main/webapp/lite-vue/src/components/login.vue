<template>
  <div class="mdl-layout__container">
    <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
      <header class="mdl-layout__header mdl-layout__header">
        <div class="mdl-layout__header-row">
          <span class="mdl-layout-title mdl-color-text--white">TSI</span>
        </div>
      </header>
      <main class="mdl-layout__content">
        <div class="mdl-card mdl-shadow--6dp">
          <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
            <h2 class="mdl-card__title-text">Login.</h2>
          </div>
          <div class="mdl-card__supporting-text">
            <form action="/api/login">
              <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="text" id="userName"/>
                <label class="mdl-textfield__label" for="userName">Username</label>
              </div>
              <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="password" id="userPassword"/>
                <label class="mdl-textfield__label" for="userPassword">Password</label>
              </div>
            </form>
          </div>
          <div class="mdl-card__actions mdl-card--border">
            <button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Log in</button>
          </div>
        </div>
      </main>
      <ljun-footer></ljun-footer>
    </div>
  </div>
</template>
<style scoped>
  div.mdl-card.mdl-shadow--6dp {
    margin: auto;
  }

  .mdl-layout__content {
    padding: 10vh;
  }
</style>
<script>
  import ljunFooter from "./ljun-footer.vue"
  export default{
    components: {
      ljunFooter
    }
  }
</script>
