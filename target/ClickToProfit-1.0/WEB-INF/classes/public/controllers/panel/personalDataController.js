var app = angular.module('clicktoprofit');
app.controller('personalDataController', ['$cookieStore', '$scope', '$http', function ($cookieStore, $scope, $http) {

    $scope.page.parentproperty = "Dati personali";
    var current_birthdate;
    var check = false;

    $http.get('/api/v1/getUserData', {params: {"id_user": $cookieStore.get("usr_id")}}).success(function (data) {
        $scope.name = data['name'];
        $scope.surname = data['surname'];
        $scope.email = data['email'];
        $scope.birthdate = data['birthdate'];
        current_birthdate = data['birthdate'];
    });

    document.getElementById('change_data_button').onclick = function () {
        if (!check) {
            document.getElementById('day').disabled = false;
            document.getElementById('month').disabled = false;
            document.getElementById('year').disabled = false;
            check = true;
        } else {
            document.getElementById('day').disabled = true;
            document.getElementById('month').disabled = true;
            document.getElementById('year').disabled = true;
            check = false;
        }
    }

    $scope.updateUserData = function () {
        if ($scope.name && $scope.surname && $scope.email && check) {
            if ($scope.day && $scope.month && $scope.year) {
                $http.get('api/v1/updateUserData', {
                    params: {
                        "id_user": $cookieStore.get("usr_id"),
                        "email": $scope.email,
                        "name": $scope.name,
                        "surname": $scope.surname,
                        "birthdate": $scope.year.id + '-' + $scope.month.id + '-' + $scope.day.id
                    }
                }).then(function () {
                    Materialize.toast('Dati personali modificati corretamente', 2000);
                    $scope.page.name = $scope.name;
                    $scope.page.surname = $scope.surname;
                    $scope.birthdate = $scope.year.id + '-' + $scope.month.id + '-' + $scope.day.id;
                },function() {
                    Materialize.toast('SERVER ERROR', 2000);
                });
            } else {
                Materialize.toast('Inserisci una data', 2000);
            }
        } else if ($scope.name && $scope.surname && $scope.email && !check) {
            $http.get('api/v1/updateUserData', {
                params: {
                    "id_user": $cookieStore.get("usr_id"),
                    "email": $scope.email,
                    "name": $scope.name,
                    "surname": $scope.surname,
                    "birthdate": current_birthdate
                }
            }).then(function () {
                $cookieStore.remove("usr_email");
                $cookieStore.put("usr_email", $.md5($scope.email));
                $scope.page.name = $scope.name;
                $scope.page.surname = $scope.surname;
                Materialize.toast('Dati personali modificati corretamente', 2000);
            },function() {
                Materialize.toast('SERVER ERROR', 2000);
            });
        } else {
            Materialize.toast('Compila tutti i campi', 2000);
        }
    }

    $scope.days = {
        availableOptions: [
            {id: '01', name: '1'},
            {id: '02', name: '2'},
            {id: '03', name: '3'},
            {id: '04', name: '4'},
            {id: '05', name: '5'},
            {id: '06', name: '6'},
            {id: '07', name: '7'},
            {id: '08', name: '8'},
            {id: '09', name: '9'},
            {id: '10', name: '10'},
            {id: '11', name: '11'},
            {id: '12', name: '12'},
            {id: '13', name: '13'},
            {id: '14', name: '14'},
            {id: '15', name: '15'},
            {id: '16', name: '16'},
            {id: '17', name: '17'},
            {id: '18', name: '18'},
            {id: '19', name: '19'},
            {id: '20', name: '20'},
            {id: '21', name: '21'},
            {id: '22', name: '22'},
            {id: '23', name: '23'},
            {id: '24', name: '24'},
            {id: '25', name: '25'},
            {id: '26', name: '26'},
            {id: '27', name: '27'},
            {id: '28', name: '28'},
            {id: '29', name: '29'},
            {id: '30', name: '30'},
            {id: '31', name: '31'}
        ],
    };

    $scope.months = {
        availableOptions: [
            {id: '01', name: 'Gennaio'},
            {id: '02', name: 'Febbraio'},
            {id: '03', name: 'Marzo'},
            {id: '04', name: 'Aprile'},
            {id: '05', name: 'Maggio'},
            {id: '06', name: 'Giugno'},
            {id: '07', name: 'Luglio'},
            {id: '08', name: 'Agosto'},
            {id: '09', name: 'Settembre'},
            {id: '10', name: 'Ottobre'},
            {id: '11', name: 'Novembre'},
            {id: '12', name: 'Dicembre'}
        ],
    };

    $scope.years = {
        availableOptions: [
            {id: '2016', name: '2016'},
            {id: '2015', name: '2015'},
            {id: '2014', name: '2014'},
            {id: '2013', name: '2013'},
            {id: '2012', name: '2012'},
            {id: '2011', name: '2011'},
            {id: '2010', name: '2010'},
            {id: '2009', name: '2009'},
            {id: '2008', name: '2008'},
            {id: '2007', name: '2007'},
            {id: '2006', name: '2006'},
            {id: '2005', name: '2005'},
            {id: '2004', name: '2004'},
            {id: '2003', name: '2003'},
            {id: '2002', name: '2002'},
            {id: '2001', name: '2001'},
            {id: '2000', name: '2000'},
            {id: '1999', name: '1999'},
            {id: '1998', name: '1998'},
            {id: '1997', name: '1997'},
            {id: '1996', name: '1996'},
            {id: '1995', name: '1995'},
            {id: '1994', name: '1994'},
            {id: '1993', name: '1993'},
            {id: '1992', name: '1992'},
            {id: '1991', name: '1991'},
            {id: '1990', name: '1990'},
            {id: '1989', name: '1989'},
            {id: '1988', name: '1988'},
            {id: '1987', name: '1987'},
            {id: '1986', name: '1986'},
            {id: '1985', name: '1985'},
            {id: '1984', name: '1984'},
            {id: '1983', name: '1983'},
            {id: '1982', name: '1982'},
            {id: '1981', name: '1981'},
            {id: '1980', name: '1980'},
            {id: '1979', name: '1979'},
            {id: '1978', name: '1978'},
            {id: '1977', name: '1977'},
            {id: '1976', name: '1976'},
            {id: '1975', name: '1975'},
            {id: '1974', name: '1974'},
            {id: '1973', name: '1973'},
            {id: '1972', name: '1972'},
            {id: '1971', name: '1971'},
            {id: '1970', name: '1970'},
            {id: '1969', name: '1969'},
            {id: '1968', name: '1968'},
            {id: '1967', name: '1967'},
            {id: '1966', name: '1966'},
            {id: '1965', name: '1965'},
            {id: '1964', name: '1964'},
            {id: '1963', name: '1963'},
            {id: '1962', name: '1962'},
            {id: '1961', name: '1961'},
            {id: '1960', name: '1960'},
            {id: '1959', name: '1959'},
            {id: '1958', name: '1958'},
            {id: '1957', name: '1957'},
            {id: '1956', name: '1956'},
            {id: '1955', name: '1955'},
            {id: '1954', name: '1954'},
            {id: '1953', name: '1953'},
            {id: '1952', name: '1952'},
            {id: '1951', name: '1951'},
            {id: '1950', name: '1950'},
            {id: '1949', name: '1949'},
            {id: '1948', name: '1948'},
            {id: '1947', name: '1947'},
            {id: '1946', name: '1946'},
            {id: '1945', name: '1945'},
            {id: '1944', name: '1944'},
            {id: '1943', name: '1943'},
            {id: '1942', name: '1942'},
            {id: '1941', name: '1941'},
            {id: '1940', name: '1940'},
            {id: '1939', name: '1939'},
            {id: '1938', name: '1938'},
            {id: '1937', name: '1937'},
            {id: '1936', name: '1936'},
            {id: '1935', name: '1935'},
            {id: '1934', name: '1934'},
            {id: '1933', name: '1933'},
            {id: '1932', name: '1932'},
            {id: '1931', name: '1931'},
            {id: '1930', name: '1930'},
            {id: '1929', name: '1929'},
            {id: '1928', name: '1928'},
            {id: '1927', name: '1927'},
            {id: '1926', name: '1926'},
            {id: '1925', name: '1925'},
            {id: '1924', name: '1924'},
            {id: '1923', name: '1923'},
            {id: '1922', name: '1922'},
            {id: '1921', name: '1921'},
            {id: '1920', name: '1920'},
            {id: '1919', name: '1919'},
            {id: '1918', name: '1918'},
            {id: '1917', name: '1917'},
            {id: '1916', name: '1916'},
            {id: '1915', name: '1915'},
            {id: '1914', name: '1914'},
            {id: '1913', name: '1913'},
            {id: '1912', name: '1912'},
            {id: '1911', name: '1911'},
            {id: '1910', name: '1910'},
            {id: '1909', name: '1909'},
            {id: '1908', name: '1908'},
            {id: '1907', name: '1907'},
            {id: '1906', name: '1906'},
            {id: '1905', name: '1905'},
            {id: '1904', name: '1904'},
            {id: '1903', name: '1903'},
            {id: '1902', name: '1902'},
            {id: '1901', name: '1901'},
            {id: '1900', name: '1900'}
        ],
    };

}])
;