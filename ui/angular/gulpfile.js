var gulp = require('gulp');
var concat = require('gulp-concat');
var clean = require('gulp-clean');
var less = require('gulp-less');
var Server = require('karma').Server;

gulp.task('default', ['concatJS', 'concatLESS', 'less']);


gulp.task('less', ['concatLESS'], function () {
    return gulp.src('./built/qac-forum-concat.less')
        .pipe(less({
            paths: ['built/qac-forum-concat.css']
        }))
        .pipe(gulp.dest('built/'));
});

gulp.task('concatLESS', ['cleanBuiltFolder'], function() {
    return gulp.src(['less/variables.less', 'less/*.less', 'less/**/*.less'])
        .pipe(concat('qac-forum-concat.less'))
        .pipe(gulp.dest('./built/'))
});

gulp.task('concatJS', ['cleanBuiltFolder'], function() {
    return gulp.src(['./app/*.js', './app/**/*.js'])
        .pipe(concat('qac-forum.js'))
        .pipe(gulp.dest('./built/'))
});

gulp.task('cleanBuiltFolder', function() {
    return gulp.src('built', {read: false})
        .pipe(clean());
});

gulp.task('test', function(done) {
    gulp.start('concatJS');
    new Server({
        configFile: __dirname + '/karma.conf.js',
        singleRun: true
    }, done).start();
});