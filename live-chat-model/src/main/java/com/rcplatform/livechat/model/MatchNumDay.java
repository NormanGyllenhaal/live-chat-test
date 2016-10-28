package com.rcplatform.livechat.model;

import com.bj58.spat.gaea.serializer.component.annotation.GaeaMember;
import com.bj58.spat.gaea.serializer.component.annotation.GaeaSerializable;

import java.io.Serializable;
import java.util.Date;

@Table(name = "rc_match_num_day")

public class MatchNumDay implements Serializable {
    @Id

    private Integer id;

    /**
     * 每日匹配失败次数
     */
    @Column(name = "match_fail")

    private Integer matchFail;

    /**
     * 每日匹配成功次数
     */
    @Column(name = "match_success")

    private Integer matchSuccess;

    /**
     * 每日匹配总次数
     */
    @Column(name = "match_total")

    private Integer matchTotal;

    /**
     * 每日男性匹配总次数
     */
    @Column(name = "match_boy_count")

    private Integer matchBoyCount;

    /**
     * 每日女性匹配总次数
     */
    @Column(name = "match_girl_count")

    private Integer matchGirlCount;

    /**
     * 每日女女匹配总次数
     */
    @Column(name = "match_girl_girl_count")

    private Integer matchGirlGirlCount;

    /**
     * 每日男女匹配总次数
     */
    @Column(name = "match_boy_girl_count")

    private Integer matchBoyGirlCount;

    /**
     * 每日男男匹配总次数
     */
    @Column(name = "match_boy_boy_count")

    private Integer matchBoyBoyCount;

    /**
     * 每日男性匹配成功次数
     */
    @Column(name = "match_boy_success_count")

    private Integer matchBoySuccessCount;

    /**
     * 每日女性匹配成功次数
     */
    @Column(name = "match_girl_success_count")

    private Integer matchGirlSuccessCount;

    /**
     * 每日男男匹配成功次数
     */
    @Column(name = "match_boy_boy_success_count")

    private Integer matchBoyBoySuccessCount;

    /**
     * 每日男女匹配成功次数
     */
    @Column(name = "match_boy_girl_success_count")

    private Integer matchBoyGirlSuccessCount;

    /**
     * 每日女女匹配成功次数
     */
    @Column(name = "match_girl_girl_success_count")

    private Integer matchGirlGirlSuccessCount;

    /**
     * 创建时间
     */
    @Column(name = "create_time")

    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取每日匹配失败次数
     *
     * @return match_fail - 每日匹配失败次数
     */
    public Integer getMatchFail() {
        return matchFail;
    }

    /**
     * 设置每日匹配失败次数
     *
     * @param matchFail 每日匹配失败次数
     */
    public void setMatchFail(Integer matchFail) {
        this.matchFail = matchFail;
    }

    /**
     * 获取每日匹配成功次数
     *
     * @return match_success - 每日匹配成功次数
     */
    public Integer getMatchSuccess() {
        return matchSuccess;
    }

    /**
     * 设置每日匹配成功次数
     *
     * @param matchSuccess 每日匹配成功次数
     */
    public void setMatchSuccess(Integer matchSuccess) {
        this.matchSuccess = matchSuccess;
    }

    /**
     * 获取每日匹配总次数
     *
     * @return match_total - 每日匹配总次数
     */
    public Integer getMatchTotal() {
        return matchTotal;
    }

    /**
     * 设置每日匹配总次数
     *
     * @param matchTotal 每日匹配总次数
     */
    public void setMatchTotal(Integer matchTotal) {
        this.matchTotal = matchTotal;
    }

    /**
     * 获取每日男性匹配总次数
     *
     * @return match_boy_count - 每日男性匹配总次数
     */
    public Integer getMatchBoyCount() {
        return matchBoyCount;
    }

    /**
     * 设置每日男性匹配总次数
     *
     * @param matchBoyCount 每日男性匹配总次数
     */
    public void setMatchBoyCount(Integer matchBoyCount) {
        this.matchBoyCount = matchBoyCount;
    }

    /**
     * 获取每日女性匹配总次数
     *
     * @return match_girl_count - 每日女性匹配总次数
     */
    public Integer getMatchGirlCount() {
        return matchGirlCount;
    }

    /**
     * 设置每日女性匹配总次数
     *
     * @param matchGirlCount 每日女性匹配总次数
     */
    public void setMatchGirlCount(Integer matchGirlCount) {
        this.matchGirlCount = matchGirlCount;
    }

    /**
     * 获取每日女女匹配总次数
     *
     * @return match_girl_girl_count - 每日女女匹配总次数
     */
    public Integer getMatchGirlGirlCount() {
        return matchGirlGirlCount;
    }

    /**
     * 设置每日女女匹配总次数
     *
     * @param matchGirlGirlCount 每日女女匹配总次数
     */
    public void setMatchGirlGirlCount(Integer matchGirlGirlCount) {
        this.matchGirlGirlCount = matchGirlGirlCount;
    }

    /**
     * 获取每日男女匹配总次数
     *
     * @return match_boy_girl_count - 每日男女匹配总次数
     */
    public Integer getMatchBoyGirlCount() {
        return matchBoyGirlCount;
    }

    /**
     * 设置每日男女匹配总次数
     *
     * @param matchBoyGirlCount 每日男女匹配总次数
     */
    public void setMatchBoyGirlCount(Integer matchBoyGirlCount) {
        this.matchBoyGirlCount = matchBoyGirlCount;
    }

    /**
     * 获取每日男男匹配总次数
     *
     * @return match_boy_boy_count - 每日男男匹配总次数
     */
    public Integer getMatchBoyBoyCount() {
        return matchBoyBoyCount;
    }

    /**
     * 设置每日男男匹配总次数
     *
     * @param matchBoyBoyCount 每日男男匹配总次数
     */
    public void setMatchBoyBoyCount(Integer matchBoyBoyCount) {
        this.matchBoyBoyCount = matchBoyBoyCount;
    }

    /**
     * 获取每日男性匹配成功次数
     *
     * @return match_boy_success_count - 每日男性匹配成功次数
     */
    public Integer getMatchBoySuccessCount() {
        return matchBoySuccessCount;
    }

    /**
     * 设置每日男性匹配成功次数
     *
     * @param matchBoySuccessCount 每日男性匹配成功次数
     */
    public void setMatchBoySuccessCount(Integer matchBoySuccessCount) {
        this.matchBoySuccessCount = matchBoySuccessCount;
    }

    /**
     * 获取每日女性匹配成功次数
     *
     * @return match_girl_success_count - 每日女性匹配成功次数
     */
    public Integer getMatchGirlSuccessCount() {
        return matchGirlSuccessCount;
    }

    /**
     * 设置每日女性匹配成功次数
     *
     * @param matchGirlSuccessCount 每日女性匹配成功次数
     */
    public void setMatchGirlSuccessCount(Integer matchGirlSuccessCount) {
        this.matchGirlSuccessCount = matchGirlSuccessCount;
    }

    /**
     * 获取每日男男匹配成功次数
     *
     * @return match_boy_boy_success_count - 每日男男匹配成功次数
     */
    public Integer getMatchBoyBoySuccessCount() {
        return matchBoyBoySuccessCount;
    }

    /**
     * 设置每日男男匹配成功次数
     *
     * @param matchBoyBoySuccessCount 每日男男匹配成功次数
     */
    public void setMatchBoyBoySuccessCount(Integer matchBoyBoySuccessCount) {
        this.matchBoyBoySuccessCount = matchBoyBoySuccessCount;
    }

    /**
     * 获取每日男女匹配成功次数
     *
     * @return match_boy_girl_success_count - 每日男女匹配成功次数
     */
    public Integer getMatchBoyGirlSuccessCount() {
        return matchBoyGirlSuccessCount;
    }

    /**
     * 设置每日男女匹配成功次数
     *
     * @param matchBoyGirlSuccessCount 每日男女匹配成功次数
     */
    public void setMatchBoyGirlSuccessCount(Integer matchBoyGirlSuccessCount) {
        this.matchBoyGirlSuccessCount = matchBoyGirlSuccessCount;
    }

    /**
     * 获取每日女女匹配成功次数
     *
     * @return match_girl_girl_success_count - 每日女女匹配成功次数
     */
    public Integer getMatchGirlGirlSuccessCount() {
        return matchGirlGirlSuccessCount;
    }

    /**
     * 设置每日女女匹配成功次数
     *
     * @param matchGirlGirlSuccessCount 每日女女匹配成功次数
     */
    public void setMatchGirlGirlSuccessCount(Integer matchGirlGirlSuccessCount) {
        this.matchGirlGirlSuccessCount = matchGirlGirlSuccessCount;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", matchFail=").append(matchFail);
        sb.append(", matchSuccess=").append(matchSuccess);
        sb.append(", matchTotal=").append(matchTotal);
        sb.append(", matchBoyCount=").append(matchBoyCount);
        sb.append(", matchGirlCount=").append(matchGirlCount);
        sb.append(", matchGirlGirlCount=").append(matchGirlGirlCount);
        sb.append(", matchBoyGirlCount=").append(matchBoyGirlCount);
        sb.append(", matchBoyBoyCount=").append(matchBoyBoyCount);
        sb.append(", matchBoySuccessCount=").append(matchBoySuccessCount);
        sb.append(", matchGirlSuccessCount=").append(matchGirlSuccessCount);
        sb.append(", matchBoyBoySuccessCount=").append(matchBoyBoySuccessCount);
        sb.append(", matchBoyGirlSuccessCount=").append(matchBoyGirlSuccessCount);
        sb.append(", matchGirlGirlSuccessCount=").append(matchGirlGirlSuccessCount);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}