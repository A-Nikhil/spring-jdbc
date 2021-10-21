package com.anikhil.sqllib.query;

import org.springframework.stereotype.Component;

import com.anikhil.sqllib.exceptions.IncorrectOrderException;

import java.util.List;

@Component
public class SQLQueryOrderValidation {

    private final SQLQueryOrder select = new SQLQueryOrder(
            new SQLQueryKeyword[]{SQLQueryKeyword.INSERT, SQLQueryKeyword.UPDATE,
                    SQLQueryKeyword.DELETE},
            null
    );

    private final SQLQueryOrder insert = new SQLQueryOrder(
            new SQLQueryKeyword[]{SQLQueryKeyword.SELECT, SQLQueryKeyword.UPDATE,
                    SQLQueryKeyword.DELETE},
            null
    );

    private final SQLQueryOrder update = new SQLQueryOrder(
            new SQLQueryKeyword[]{SQLQueryKeyword.INSERT, SQLQueryKeyword.SELECT,
                    SQLQueryKeyword.DELETE},
            null
    );


    private final SQLQueryOrder delete = new SQLQueryOrder(
            new SQLQueryKeyword[]{SQLQueryKeyword.INSERT, SQLQueryKeyword.UPDATE,
                    SQLQueryKeyword.SELECT},
            null
    );

    private final SQLQueryOrder from = new SQLQueryOrder(
            null,
            new SQLQueryKeyword[]{SQLQueryKeyword.SELECT, SQLQueryKeyword.DELETE}
    );

    private final SQLQueryOrder values = new SQLQueryOrder(
            null,
            new SQLQueryKeyword[]{SQLQueryKeyword.INSERT}
    );

    private final SQLQueryOrder set = new SQLQueryOrder(
            new SQLQueryKeyword[]{SQLQueryKeyword.UPDATE},
            null
    );

    private final SQLQueryOrder where = new SQLQueryOrder(
            new SQLQueryKeyword[]{SQLQueryKeyword.SELECT, SQLQueryKeyword.UPDATE,
                    SQLQueryKeyword.DELETE, SQLQueryKeyword.SET, SQLQueryKeyword.FROM},
            null
    );

    public void validateOrder(SQLQueryKeyword[] ongoingOrder, String keyword) throws IncorrectOrderException {
        SQLQueryOrder order;
        keyword = keyword.toLowerCase();
        switch (keyword) {
            case "select":
                order = this.select;
                break;
            case "insert":
                order = this.insert;
                break;
            case "delete":
                order = this.delete;
                break;
            case "update":
                order = this.update;
                break;
            case "from":
                order = this.from;
                break;
            case "set":
                order = this.set;
                break;
            case "values":
                order = this.values;
                break;
            default:
                order = this.where;
        }

        validateOrder(ongoingOrder, order);
    }

    private void validateOrder(SQLQueryKeyword[] ongoingOrder, SQLQueryOrder keywordOrder)
            throws IncorrectOrderException {
        final List<SQLQueryKeyword> notAcceptedKeywords = keywordOrder.getNotAcceptedKeywords();
        final List<SQLQueryKeyword> predecessor = keywordOrder.getPredecessor();
        for (SQLQueryKeyword keyword : ongoingOrder) {
            if (notAcceptedKeywords.contains(keyword)) {
                throw new IncorrectOrderException(keyword.toString(), keyword.toString(), true);
            } else if (!predecessor.contains(keyword)) {
                throw new IncorrectOrderException(keyword.toString(), keywordOrder.toString(), false);
            }
        }
    }
}
