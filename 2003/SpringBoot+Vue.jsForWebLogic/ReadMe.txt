제일 빠르게 시작

1. springboot

https://start.spring.io/

* dependencies

Lombok
Spring Web
JDBC API

2. 1) 터미널로
    npm i -g @vue/cli
    npm i -g @vue/cli-init
  하고 
  2) 해당 pjt 폴더에서
   vue init webpack frontend
   하면
  3) 3가지 설정
    Project name
    Project description
    Author
  
   4) Vue build standalone
      vue-router yes
      eslint yes
      ...
     use npm

3. vue에서 webpack 번들링 설정
  config>index.js
   build 에서 
   기존
    // Template for index.html
    index: path.resolve(__dirname, '../dist/index.html'),

    // Paths
    assetsRoot: path.resolve(__dirname, '../dist'),

    신규
    // Template for index.html
    index: path.resolve(__dirname, '../../src/main/resources/static/index.html'),

    // Paths
    assetsRoot: path.resolve(__dirname, '../../src/main/resources/static'),

   -> npm run build

4. 다시 java project에서 jdbc 했을경우 반드시 연결해야함