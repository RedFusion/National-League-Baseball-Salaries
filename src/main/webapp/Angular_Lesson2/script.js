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
            // ��� -> ������
            elm.bind('blur', function() {
                scope.$apply(function() {
                    ctrl.$setViewValue(elm.html());
                });
            });

            // ������ -> ���
            ctrl.$render = function(value) {
                elm.html(value);
            };

            // �������� ���������� �������� �� DOM
            ctrl.$setViewValue(elm.html());
        }
    };
});

angular.module('timeExampleModule', []).
    // ������� �����, ��������� ��� �������� ������
    // � ������� ��� time
    factory('time', function($timeout) {
        var time = {};

        (function tick() {
            time.now = new Date().toString();
            $timeout(tick, 1000);
        })();
        return time;
    });

// �������� �������� �� ��, ��� ����� ������ ��������� time
// � ������ ����� ������������. �� ����� ������ ������.
function ClockCtrl($scope, time) {
    $scope.time = time;
}