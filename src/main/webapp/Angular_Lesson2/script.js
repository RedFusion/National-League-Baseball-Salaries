/**
 * Created by Menkin on 18.11.2015.
 */
function GreetCtrl($scope) {
    $scope.name = 'World';
}

function ListCtrl($scope) {
    $scope.names = ['Igor', 'Misko', 'Vojta'];
}

function MyCtrl($scope) {
    $scope.action = function() {
        $scope.name = 'OK';
    }
    $scope.name = 'MyWorld';
}

angular.module('directive', []).directive('contenteditable', function() {
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            // вид -> модель
            elm.bind('blur', function() {
                scope.$apply(function() {
                    ctrl.$setViewValue(elm.html());
                });
            });

            // модель -> вид
            ctrl.$render = function(value) {
                elm.html(value);
            };

            // загрузка начального значения из DOM
            ctrl.$setViewValue(elm.html());
        }
    };
});

angular.module('timeExampleModule', []).
    // Объявим новый, доступный для инъекций объект
    // и назовем его time
    factory('time', function($timeout) {
        var time = {};

        (function tick() {
            time.now = new Date().toString();
            $timeout(tick, 1000);
        })();
        return time;
    });

// Обратите внимание на то, что можно просто запросить time
// и запрос будет удовлетворен. Не нужно ничего искать.
function ClockCtrl($scope, time) {
    $scope.time = time;
}