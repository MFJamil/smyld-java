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
                  @click="handleClick(item.action);"
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
           @click="handleToolbar(item.action);"
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

        public menus=[
          { id: 'menu1',
            title: 'Dateie',
            subMenu: [
              {title: 'Öffnen'},
              {title: 'Speicher'},
              {title: 'Theme', action:'changeTheme'},
              {title: 'Beenden'},
            ]
          },
          { id: 'menu2',
            title: 'Bearbeiten',
            subMenu: [
              {title: 'Edit'},
              {title: 'Copy'},
              {title: 'Einfugen'},
            ]
          },
          { id: 'menu3',
            title: 'Hilfe',
            subMenu: [
              {title: 'Portal Engine'},
              {title: 'About'},
            ]
          },
        ];

        public toolbar=[
          { id: 'toolbar1',
            align:'left',
            items: [
              {icon: 'mdi-close-circle-outline'},
              {icon: 'mdi-undo-variant'},
              {icon: 'mdi-pause', action:'editDocument'},
              {icon: 'mdi-file-document-edit-outline'},
            ]
          },
          { id: 'toolbar2',
            align:'right',
            items: [
              {icon: 'mdi-compare', action: 'changeTheme'},
              {icon: 'mdi-help-circle-outline'},
              {icon: 'mdi-printer'},
              {icon: 'mdi-exit-to-app', action:'logOut'},

            ]
          },
        ];

        handleToolbar(action:string):void{
          if (action){
            console.log("Handling the toolbar action " + action);
            this.$emit(action);
          }
        }
        handleClick(action:string):void{
          if (action){
            console.log("Handling the Menu action " + action);
            this.$emit(action);
          }
        }

    }


</script>