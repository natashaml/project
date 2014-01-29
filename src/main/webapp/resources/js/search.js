$('#search').keypress(function(e) {
			if (e.which == 13) {
				window.location.href = "http://localhost:8080/booksjournal/search/" + $('#search').val() + "?searchBy=title";
				return false;
			}
		});