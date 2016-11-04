$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

var Users = Backbone.Collection.extend({
    url: '/Profile'
});

var User = Backbone.Model.extend({
    urlRoot: '/AddUser',
});

var Teams = Backbone.Collection.extend({
	url:'/ProfileManager'
});

var Team = Backbone.Model.extend({
	urlRoot: '/addTeam'
});



var UserList = Backbone.View.extend({
    el: '#primary-content',
    render:function(){
    var that = this;    
    var users = new Users();
    
   users.fetch({
	   success: function(){
		   var template = _.template($('#profileTemplate ').html());
		   that.$el.html(template({
               users: users.models
               
           }));
		   var simplebar = new Nanobar();
			simplebar.go(100);
		   return true;
	   }
   })
  }
});

var TeamList = Backbone.View.extend({
    el: '#primary-content',
    render:function(){
    var that = this;    
    var members = new Teams();
    
    members.fetch({
	   success: function(){
		   var template = _.template($('#TeamPeople ').html());
		   that.$el.html(template({
			   members: members.models
               
           }));
		   var simplebar = new Nanobar();
			simplebar.go(100);
		   return true;
	   }
   })
  }
});





var Router = Backbone.Router.extend({
	routes:{
		'' 				: 'home',
		'Teamstatus'	: 'status'
	}
});

var userList = new UserList();
var teamList = new TeamList();

var router = new Router();
router.on('route:home', function(){
	userList.render();
});
router.on('route:status', function(){
	teamList.render();
});


Backbone.history.stop();
Backbone.history.start();