var app = angular.module("reviewApp", []);
app.controller("reviewlCtrl", function ($scope, $rootScope, $location) {


  $scope.init = function () {
    $scope.score = 10;
    $scope.movieID = $location.search().movieID;
    $scope.downUrl = "/movie/uploadDownload/downloadImage";
    $scope.getMovieInfo();
    $scope.getReviewList();
  }
  $('#comment').on('hide.bs.modal', function () {
    $('#form_comment').bootstrapValidator('resetForm');
  });


  $scope.getMovieInfo = function () {
    var req = {
      id: $scope.movieID
    }
    $.ajax({
      url: "/movie/queryMovieByCond",
      type: "GET",
      contentType: "application/json",
      data: req,
      success: function (data) {
        $rootScope.$apply(function () {
          $scope.movie = data.movieList[0];
        })
      },
      error: function (data) {

      }
    })
  }
  $scope.getReviewList = function () {
    $.ajax({
      url: "/movie/getUserInfo",
      type: "GET",
      contentType: "application/json",
      // dataType: "json",
      success: function (data) {
        $rootScope.$apply(function () {
          $scope.userName = data.userName
          $scope.userId = data.id
          if ($scope.userName !== null && $scope.userName !== '') {
            var req = {
              movie: {},
              user: {
                id: $scope.userId
              }
            }
            $.ajax({
              url: "/movie/queryReviewByCond",
              type: "post",
              contentType: "application/json",
              data: JSON.stringify(req),
              success: function (data) {
                $rootScope.$apply(function () {
                  $scope.total = data.total;
                  $scope.reviewList = data.reviewList;
                })
              },
              error: function (data) {

              }
            })
          } else {
            top.location.href = "/movie/view/login/login.html";
          }
        })
      },
      error: function (data) {

      }
    })

  }

  $scope.gotoDetail = function (id) {
    window.location.href = "/movie/view/movie/detail/movieDetail.html?movieID=" + id
  }


})

app.config(['$locationProvider', function ($locationProvider) {
  $locationProvider.html5Mode({
    enabled: true,
    requireBase: false
  });
}]);

app.filter("newdate", function () {
  return function (date) {
    return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " " + date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12)
  }
})