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
                self.setState({currentPage: 1});
            });
    },

    loadPage: function(event) {
        let requestedPage = event.currentTarget.dataset.pagenumber;
        var self = this;
        self.setState({currentPage: requestedPage});
        let promise = fetch('/questionnaires/get?pagenumber=' + requestedPage)
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

        let PaginatorElement = this.state.pagesCount > 0 ? <Paginator currentPage={this.state.currentPage} loadPage={this.loadPage} countPages={this.state.pagesCount}/> : '';
        return(
            <div className="wrapper">
                <ContentHeader/>
                <Filters/>
                <div className="questionnaire-list__items">
                    {Questionnaries}
                </div>
                {PaginatorElement}
            </div>
        )
    }
});

components.register("QuestionnaireList", QuestionnaireList);