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
    urlRoot: function(){
    	return '/ApplyLeave.do';
    }
});

var Status = Backbone.Collection.extend({
	url: function() {
        return '/Status';
    }
});

var TeamStatus = Backbone.Collection.extend({
	url : function(){
		return '/TeamLeavestatus';
	}
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

var TeamStatusList = Backbone.View.extend({
    el: '#primary-content',
    render:function(){
    var that = this;    
    var teamStatus = new TeamStatus();
    teamStatus.fetch({
    success: function(){
		   var template = _.template($('#teamLeaveStatus ').html());
		   that.$el.html(template({
			   teamStatus: teamStatus.models
           }));

		   var simplebar = new Nanobar();
			simplebar.go(100);
		   return true;
	   }
   });
  }
});


var StatusList = Backbone.View.extend({
	el: '#primary-content',
	render: function(){
		var that = this;
		var status = new Status();
		status.fetch({
			success: function(){
				var template = _.template($('#statusTemplate').html());
				that.$el.html(template({
					status: status.models
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
		var simplebar = new Nanobar();
		simplebar.go(100);
		var template = _.template($('#applyTemplate').html());
		this.$el.html(template);
		console.log('successs');
	},
	events:{
		 "submit .leaveApply": "LeaveRequest"
	},
	LeaveRequest: function(ev){
		console.log("success112")
		var leaveApply = $(ev.currentTarget).serializeObject();
		var leave = new Leave();
		leave.save(leaveApply);
	}
});

var LeaveApprove = Backbone.View.extend({
	el: '#primary-content',
	
    render: function(options) {
    	var that = this;
    	$( "#Status" ).ready(function() {
    		setTimeout(function(){
    		$(".toggle").hide();
    		}, 200);
    		
    		$(document).on('change', '#Status', function(e) { 
        		$("#Status").change(function(){
					if($(this).val() == "Approved"){
		            	$(".toggle").show();
		            }else{
		            	$(".toggle").hide();
		            }
		        });
       		});
    		
    	});
         var leave = new Leave({
            id: options.id
        });
        leave.fetch({
            success: function(leave) {
            	var template = _.template($('#leaveEdit').html());
                that.$el.html(template({
                    leave: leave
                }));
            }
        });  
    },
});



var leaveRouter = Backbone.Router.extend({
	routes:{
		'leaveDetail'	: 'leave',
		'ApplyLeave' 	: 'apply',
		'Status' 	 	: 'status',
		'TeamLeaveStatus' : 'teamS',
		'approve/:id': 'approve',
		'decline/:id': 'decline',
	}
});

var leaveList = new LeaveList();
var leaveApply = new LeaveApply();
var statusList = new StatusList();
var teamStatus = new TeamStatus();
var teamStatusList = new TeamStatusList();
var leaveApprove = new LeaveApprove();

var router = new leaveRouter();

router.on('route:leave', function(){
	leaveList.render();
});

router.on('route:status', function(){
	statusList.render();
});


router.on('route:apply', function(){
	leaveApply.render();
});

router.on('route:teamS', function(){
	teamStatusList.render();
});

router.on('route:approve', function(id) {
	console.log(id);
	leaveApprove.render({
		id : id
	});
});


Backbone.history.stop();
Backbone.history.start();