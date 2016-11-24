var CreateQuestionnaireForm = React.createClass({
    getInitialState: function() {
        return({questions: []});
    },

    addQuestion: function(e) {
        let previousQuestionsState = this.state.questions;
        let nameQuestion = 'question_' + previousQuestionsState.length;
        let typeQuestion = e.currentTarget.dataset.type;
        previousQuestionsState.push({name: nameQuestion, type: typeQuestion});
        this.setState({questions: previousQuestionsState});
    },

    render: function() {
        let questions = this.state.questions.map(function (item) {
            return <CreateQuestionForm key={item.name} name={item.name} type={item.type}/>;
        });
        return(
            <form method="post" action="/questionnaire/save">
                <QuestionnaireEditHeader/>
                <div>
                    <QuestionTypeSwitcher addQuestion={this.addQuestion}/>
                </div>

                <div>
                    {questions}
                </div>
            </form>
        );
    }
});

components.register("CreateQuestionnaireForm", CreateQuestionnaireForm);