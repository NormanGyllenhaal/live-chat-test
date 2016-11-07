//导入工具包 require('node_modules里对应模块')
var gulp = require('gulp'), //本地安装gulp所用到的地方
    minifycss = require('gulp-minify-css'),
    concat = require('gulp-concat'),
    uglify = require('gulp-uglify'),
    rename = require('gulp-rename'),
    //jshint = require('gulp-jshint'),
    less = require('gulp-less');
var sftp = require('gulp-sftp');
var obfuscate = require('gulp-obfuscate');

/*//定义一个testLess任务（自定义任务名称）
gulp.task('testLess', function () {
    gulp.src('src/less/index.less') //该任务针对的文件
        .pipe(less()) //该任务调用的模块
        .pipe(gulp.dest('src/css')); //将会在src/css下生成index.css
});*/

gulp.task('ftpTask', function () {
    return gulp.src('assets/*')
        .pipe(sftp({
            host: '45.79.0.215',
            user: 'root',
            pass: 'sdjkdhie$@*&df@#sDFCFqx&mM',
            remotePath:'/data/www/video-chat/admin'
        }));
});

/*
gulp.task('ftpTaskCss', function () {
    return gulp.src('assets/css/!*')
        .pipe(sftp({
            host: '45.79.0.215',
            user: 'root',
            pass: 'sdjkdhie$@*&df@#sDFCFqx&mM',
            remotePath:'/data/www/video-chat/css'
        }));
});

gulp.task('ftpTaskJs', function () {
    return gulp.src('assets/js/!*')
        .pipe(sftp({
            host: '45.79.0.215',
            user: 'root',
            pass: 'sdjkdhie$@*&df@#sDFCFqx&mM',
            remotePath:'/data/www/video-chat/js'
        }));
});
*/

/*
//压缩css
gulp.task('minifycss', function () {
    return gulp.src('src/css/!*.css')    //需要操作的文件
        .pipe(rename({suffix: '.min'}))   //rename压缩后的文件名
        .pipe(minifycss())   //执行压缩
        .pipe(gulp.dest('dist/css'));   //输出文件夹
});

//压缩,合并 js
gulp.task('minifyjs', function () {
    return gulp.src('src/js/check.js')      //需要操作的文件
        .pipe(uglify())    //压缩
        .pipe(rename({suffix: '.min'}))   //rename压缩后的文件名
        .pipe(gulp.dest('dist/js'));       //输出到文件夹
});*/




gulp.task('default',['ftpTask']); //定义默认任务 elseTask为其他任务，该示例没有定义elseTask任务/**

