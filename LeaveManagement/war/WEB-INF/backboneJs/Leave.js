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

var Leaves = Backbone.Collection.extend({
	url: function() {
        return '/Leave';
    }
});

var Leave = Backbone.Model.extend({
    urlRoot: '/LeaveAdd',
});

var LeaveList = Backbone.View.extend({
    el: '#primary-content',
    render:function(){
    var that = this;    
    var leaves = new Leaves();
    leaves.fetch({
    success: function(){
		   var template = _.template($('#leaveTemplate ').html());
		   that.$el.html(template({
			   leaves: leaves.models
           }));

		   var simplebar = new Nanobar();
			simplebar.go(100);
		   return true;
	   }
   });
  }
});


var LeaveApply = Backbone.View.extend({
	el:'#primary-content',
	render:function(){
		var template = _.template($('#applyTemplate ').html());
		this.$el.html(template);
		console.log('successs');
	}
});

var leaveRouter = Backbone.Router.extend({
	routes:{
		'leaveDetail' : 'leave',
		'ApplyLeave' : 'apply'
	}
});

var leaveList = new LeaveList();
var leaveApply = new LeaveApply();

var router = new leaveRouter();

router.on('route:leave', function(){
	leaveList.render();
});

router.on('route:apply', function(){
	leaveApply.render();
})

Backbone.history.stop();
Backbone.history.start();