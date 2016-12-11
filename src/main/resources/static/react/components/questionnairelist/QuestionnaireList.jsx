var QuestionnaireList = React.createClass({

    getInitialState: function() {
        return({questionnaries: []});
    },

    componentWillMount: function() {
        this.loadQuestionnaire();
    },

    loadQuestionnaire: function() {
        var self = this;
        let promise = fetch('/questionnaires/get')
            .then(function(response) {
                return response.json();
            })
            .then(function(result){
                self.setState({questionnaries: result.questionnaires});
                self.setState({pagesCount: result.pagesCount});
            });
    },

    render: function() {
        let Questionnaries = this.state.questionnaries.map(function (item) {
            return <QuestionnaireItem key={item.idQuestionnaire} idQuestionnaire={item.idQuestionnaire} title={item.title}/>;
        });

        let PaginatorElement = this.state.pagesCount > 0 ? <Paginator countPages={this.state.pagesCount}/> : '';
        return(
            <div className="wrapper">
                <ContentHeader/>
                <Filters/>
                <div>
                    {Questionnaries}
                </div>
                {PaginatorElement}
            </div>
        )
    }
});

components.register("QuestionnaireList", QuestionnaireList);