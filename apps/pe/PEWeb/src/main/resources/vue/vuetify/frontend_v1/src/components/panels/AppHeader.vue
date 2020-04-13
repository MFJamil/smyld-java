<template>

    <v-app-bar
      extended
      extension-height="80px"
      collapse-on-scroll
      app
      min-width="200px"
      color="secondary"
      :class="theme"
      :dark="themeDark"
      >

      <v-app-bar-nav-icon> </v-app-bar-nav-icon>
      <v-toolbar-title>{{appTitle}}</v-toolbar-title>

      <v-spacer></v-spacer>
      <div class="d-flex align-center">
        <v-img
          alt="KFW Logo"
          class="shrink mr-2"
          contain
          src="../../assets/${app_logo}"
          transition="scale-transition"
          width="100"
        />

      </div>
      <template v-slot:extension >
        <v-container  fluid >
        <v-row fluid no-gutters>
          <v-col>
            <v-menu
              v-for="(menu,id) in menus"
              :key="id"
              bottom
              origin="center center"
              offset-y
              transition="slide-y-transition"
            >
              <template v-slot:activator="{ on }">
                <v-btn
                  text small
                  v-on="on"
                  width="120px"
                >
                  {{menu.title}}
                </v-btn>
              </template>
              <v-list dense>
                <v-list-item
                  v-for="(item, id) in menu.subMenu"
                  :key="id"
                  @click="handleMenuAction  (item.action);"
                >
                  <v-list-item-title>{{ item.title }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </v-col>
        </v-row>
      <v-row  no-gutters>
        <v-col
          v-for="(toolbar,id) in toolbar"
          :key="id"
          :align=toolbar.align
        >
          <v-btn
          text fab small
          v-for="(item,id) in toolbar.items"
          :key="id"
           @click="handleMenuAction(item.action);"
            ><v-icon small>{{item.icon}}</v-icon></v-btn>
        </v-col>
      </v-row>
        </v-container>
    </template>
  </v-app-bar>
</template>


<script lang="ts">
    import { Component, Vue, Prop } from 'vue-property-decorator';

    @Component
    export default class AppHeader extends Vue {
        @Prop()
        public theme! :string;
        @Prop()
        public appTitle! :string;
        @Prop({default:true})
        public themeDark! :boolean;

        public menus=${main_menu};

        public toolbar=[
          ${app_toolbars}
        ];

        handleMenuAction(action:string):void{
          if (action){
            //console.log("Handling the toolbar action " + action);
            this.$emit('runAction',action);
          }
        }

    }


</script>