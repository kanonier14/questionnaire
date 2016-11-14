var CreateQuestionnaireForm = React.createClass({
    getInitialState: function() {
        return({questions: []});
    },

    addQuestion: function() {
        let previousQuestionsState = this.state.questions;
        let nameQuestion = 'question_' + previousQuestionsState.length;
        previousQuestionsState.push({name: nameQuestion});
        this.setState({questions: previousQuestionsState});
    },

    render: function() {
        let questions = this.state.questions.map(function (item) {
            return <CreateQuestionForm key={item.name} name={item.name}/>;
        });
        return(
            <form method="post" action="/questionnaire/save">
                <QuestionnaireEditHeader/>
                <div onClick={this.addQuestion}>
                    <AddQuestionButton/>
                </div>

                <div>
                    {questions}
                </div>
            </form>
        );
    }
});

components.register("CreateQuestionnaire", CreateQuestionnaireForm);