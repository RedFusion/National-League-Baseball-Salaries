/**
 * Created by Menkin on 18.11.2015.
 */
function PhoneListCtrl($scope) {
    $scope.phones = [
        {"name": "Nexus S",
            "snippet": "Fast just got faster with Nexus S.",
            "age": 0},
        {"name": "Motorola XOOM with Wi-Fi",
            "snippet": "The Next, Next Generation tablet.",
            "age": 1},
        {"name": "MOTOROLA XOOM",
            "snippet": "The Next, Next Generation tablet.",
            "age": 2}
    ];
    //default order
    $scope.orderProp = 'age';
}